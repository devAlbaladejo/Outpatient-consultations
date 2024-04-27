import { Component, OnInit } from '@angular/core';
import { DoctorModel } from 'src/app/model/doctor-model';
import { DoctorService } from 'src/app/services/doctor.service';
import { ListingModel } from 'src/app/model/listing-model';
import { ListingService } from 'src/app/services/listing.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RoomModel } from 'src/app/model/room-model';
import { AssistantModel } from 'src/app/model/assistant-model';
import { DiaryModel } from 'src/app/model/diary-model';
import { DiaryService } from 'src/app/services/diary.service';
import { AssistantService } from 'src/app/services/assistant.service';
import { RoomService } from 'src/app/services/room.service';
import { DatePipe } from '@angular/common';
import { UtilsService } from 'src/app/services/utils.service';
import * as XLSX from 'xlsx';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-listing',
  templateUrl: './listing.component.html',
  styleUrls: ['./listing.component.css']
})
export class ListingComponent implements OnInit{

  // List of data
  listListing: ListingModel[];
  listFilteredListing: ListingModel[];
  listDoctors: DoctorModel[];
  listAssistants: AssistantModel[];
  listRooms: RoomModel[];
  listActiveRooms: RoomModel[];
  listDiaries: DiaryModel[];
  
  // Dates
  weekOfYear: number;
  year: number;
  daysOfWeek: Date[] = new Array(5).fill(new Date());
  daysOfWeek2: Date[] = new Array(10).fill(new Date());
  date: Date = new Date();

  // Report
  formReport: FormGroup;
  excelName: string;
  listingReport: object[] = [];

  // Listing options
  formListing: FormGroup;
  isUpdate: boolean = false;
  role: string = '';
  oldRoom: number;
  oldScheduler: number;
  oldDate: Date;
  error: boolean = false;

  constructor(private listingService: ListingService, private doctorService: DoctorService,
    private assistantService: AssistantService, private roomService: RoomService,
    private diaryService: DiaryService, private utilsService: UtilsService, 
    private datePipe: DatePipe, private loginService: LoginService) { }

  ngOnInit(): void{
    this.getWeekOfYear();
    this.loadData();
    this.list();
    this.formListing = new FormGroup({
      id: new FormControl(),
      doctor: new FormControl('', Validators.required),
      doctorComment: new FormControl(''),
      assistant: new FormControl('', Validators.required),
      assistantComment: new FormControl(''),
      room: new FormControl('', Validators.required),
      date: new FormControl('', Validators.required),
      schedule: new FormControl('', Validators.required),
      diary: new FormControl('', Validators.required),
    });
    this.formReport = new FormGroup({
      startDate: new FormControl('', Validators.required),
      endDate: new FormControl('')
    })

    if(localStorage.getItem('message') != null && localStorage.getItem('info') != null)
      this.showMessage(localStorage.getItem('message')!,localStorage.getItem('info')!);
  }

  // Gets actual week of year or previous or next week
  getWeekOfYear(){
    let today: Date = new Date();
    localStorage.setItem('week', (localStorage.getItem('week') == null) ? Number(this.datePipe.transform(today, 'w')).toString() : ("" + localStorage.getItem('week')));
    this.year = Number(this.datePipe.transform(today, 'y'));
    this.getDaysOfWeek(Number(localStorage.getItem('week')), this.year);
  }

  // Gets all days of week
  getDaysOfWeek(week: number, year: number){
    let firstDay: number = ((week - 1) * 7);
    
    for(let day = 1; day <= 5; day++){
      this.date = new Date(year, 0, firstDay + day);
      this.daysOfWeek[day - 1] = this.date;
      this.daysOfWeek2[day - 1 + (day - 1)] = this.date;
      this.daysOfWeek2[day + (day - 1)] = this.date;
    }
  }

  // Gets all data
  loadData(){
    this.doctorService.getDoctors().subscribe(doctor => this.listDoctors = doctor.filter(e => e.active == 1));
    this.assistantService.getAssistants().subscribe(assistant => this.listAssistants = assistant.filter(e => e.active == 1));
    this.roomService.getRooms().subscribe(room => this.listRooms = room);
    this.roomService.getRooms().subscribe(room => this.listActiveRooms = room.filter(e => e.active == 1));
    this.diaryService.getDiaries().subscribe(diary => this.listDiaries = diary);
    this.role = this.loginService.getRole();
  }

  // List all listings
  list(){
    this.listingService.getListings().subscribe(resp => {
      if(resp){
        this.listListing = resp;
        this.resetForm();
        this.listFilteredListing = this.listListing.filter(e => e.date + "" >= this.utilsService.convertDate(this.daysOfWeek[0]) && e.date + "" <= this.utilsService.convertDate(this.daysOfWeek[4]))

        if(this.role == 'supervisor'){
          if(this.listFilteredListing.length == 0)
            this.listFilteredListing.push(resp[0]);
        }

        this.showListingOrButton();
      }
    });
  }

  // Shows listing if match with actual date and schedule
  // Otherwise, show button to create new listing
  showListingOrButton(){
    setTimeout(() => {document.querySelectorAll(".content").forEach(e => {
      let existListing = false;
      let roomDisabled = e.querySelectorAll(".disabledRoom").length;
      
      if(roomDisabled == 0){
        let divListings = e.getElementsByClassName("listings");

        for (let i = 0; i < divListings.length; i++) {
          let divRole = divListings[i].getElementsByClassName("role");
          for (let j = 0; j < divRole.length; j++) {
            if(divRole[j].getElementsByClassName("data").length > 0){
              let data = <HTMLElement> e.getElementsByClassName("listings")[i];
              data.style.display = "block";
              existListing = true;
              if(this.role == 'supervisor'){
                let button = divRole[j].querySelector<HTMLElement>(".emptyRoom");
                button!.style.display = "none";
              }
            }
          }
        }
        
        if(!existListing && this.role == 'supervisor'){
          let button = <HTMLElement> e.getElementsByClassName("listings")[0];
          button.style.display = "block";
        }
      }
    })},500);
  }

  // Show and clean Listing Form
  newListing(){
    this.isUpdate = false;
    this.resetForm();
  }

  // Create a listing
  saveListing(){
    this.setData();

    this.checkRoomAvailable();

    if(!this.error){
      this.listingService.saveListing(this.formListing.value).subscribe(resp => {
        if(resp){
          this.list();
          this.resetForm();
        }
      });
    }

    this.setMessage("Listing created");
  }

  // Update a listing
  updateListing(){
    this.setData();

    this.checkRoomAvailable();

    if(!this.error){
      this.listingService.updateListing(this.formListing.value.id,this.formListing.value).subscribe(resp => {
        if(resp){
          this.list(); 
          this.resetForm();
        }
      });
    }

    this.setMessage("Listing updated");
  }

  // Delete a listing
  deleteListing(){
    this.setData();
    this.listingService.deleteListing(this.formListing.value.id).subscribe(resp => {
      if(resp){
        this.list();
      }
    });

    this.setMessage("Listing deleted");
  }

  // Check if selected room is available
  checkRoomAvailable(){

    if(this.oldRoom == this.formListing.value.room.id &&
      this.oldDate == this.formListing.value.date &&
      this.oldScheduler == this.formListing.value.schedule){
        this.error =  false;
    }
    else{
      let roomBusy = this.listFilteredListing.filter(e => 
        e.room.id == this.formListing.value.room.id &&
        e.date == this.formListing.value.date &&
        e.schedule == this.formListing.value.schedule);
  
      if(Object.keys(roomBusy).length !== 0)
        this.error =  true;
    }
  }

  // Set a message
  // @param message: message to show
  setMessage(message: string){

    if(this.error){
      localStorage.setItem('message', 'This room is busy in this schedule and date');
      localStorage.setItem('info', 'Error');
    }
    else{
      localStorage.setItem('message', message);
      localStorage.setItem('info', 'Done');
    }

    window.location.reload();

  }

  // Shows a message
  // @param message: message to show
  // @param info: wheter the message is an error or infomational
  showMessage(message: string, info: string){
    this.utilsService.showAlert(message,info);

    localStorage.removeItem('message');
    localStorage.removeItem('info');
  }

  // Gets info of the selected listing
  // @param listing: Listing selected
  selectedListing(listing: any){
    this.isUpdate = true;
    this.formListing.controls['id'].setValue(listing.id);
    this.formListing.controls['doctor'].setValue(listing.doctor.id);
    this.formListing.controls['doctorComment'].setValue(listing.doctorComment);
    this.formListing.controls['assistant'].setValue((listing.assistant != null) ? listing.assistant.id : null);
    this.formListing.controls['assistantComment'].setValue(listing.assistantComment);
    this.formListing.controls['room'].setValue(listing.room.id);
    this.oldRoom = listing.room.id;
    this.formListing.controls['date'].setValue(listing.date);
    this.oldDate = listing.date;
    this.formListing.controls['schedule'].setValue(listing.schedule);
    this.oldScheduler = listing.schedule;
    this.formListing.controls['diary'].setValue((listing.diary != null) ? listing.diary.id : null);
  }

  // Establishes the listing's data
  setData(){
    this.formListing.value.doctor = this.listDoctors.filter(e => e.id = this.formListing.value.doctor)[0];
    this.formListing.value.assistant = (this.formListing.value.assistant != null) ? this.listAssistants.filter(e => e.id = this.formListing.value.assistant)[0] : null;
    this.formListing.value.room = this.listRooms.filter(e => e.id = this.formListing.value.room)[0];
    this.formListing.value.diary = (this.formListing.value.diary != null) ? this.listDiaries.filter(e => e.id = this.formListing.value.diary)[0] : null;
  }

  // Increase a week in 1
  next(){
    localStorage.setItem('week',(Number(localStorage.getItem('week')) + 1).toString());
    this.list();
    this.getWeekOfYear();
  }

  // Decrease a week in 1
  previous(){
    localStorage.setItem('week',(Number(localStorage.getItem('week')) - 1).toString());
    this.list();
    this.getWeekOfYear();
  }

  // Fill Listing form when you're going to create a listing
  // @param roomID: Room ID selected
  // @param date: Date selected
  // @param schedule: Schedule selected
  fillRoom(roomID: number, date: Date, schedule: string){
    this.formListing.controls['room'].setValue(roomID);
    this.formListing.controls['date'].setValue(this.utilsService.convertDate(date));
    this.formListing.controls['schedule'].setValue(schedule);
  }

  // Removes the selected assistant in Listing Form
  removeAssistant(){
    this.formListing.value.assistant = null;
    this.formListing.controls['assistant'].setValue(null);
  }

  // Removes the selected diary in Listing Form
  removeDiary(){
    this.formListing.value.diary = null;
    this.formListing.controls['diary'].setValue(null);
  }

  // Create a report between the indicated dates
  makeReport(){
    this.listListing.filter(e => e.date >= this.formReport.value.startDate && 
      e.date <= ((this.formReport.value.endDate == null) ? this.formReport.value.startDate : this.formReport.value.endDate)).map(e => {
        this.listingReport.push({
          Doctor: e.doctor.name,
          Doctor_Comment: e.doctorComment,
          Assistant: (e.assistant != null) ? e.assistant.name : '',
          Assistant_Comment: e.assistantComment,
          Room: e.room.name,
          Date: e.date,
          Schedule: e.schedule,
          Diary: (e.diary != null) ? e.diary.state : ''});
    });
    this.excelName = 'Listing_' + this.formReport.value.startDate +
    (this.formReport.value.endDate == null ? '' : '_' + this.formReport.value.endDate) + '.xlsx';
    this.exportToExcel();
  }
  
  // Function to export data to Excel file
  exportToExcel(): void {
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(this.listingReport);

    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Sheet1');

    XLSX.writeFile(book, this.excelName);

    this.resetForm();
  }

  // Disable or enable the field to insert the end date
  disableEnableEndDate(checked: boolean){

    if (checked) {
      this.formReport.controls['endDate'].disable();
      this.formReport.controls['endDate'].setValue(null);
    } else {
      this.formReport.controls['endDate'].enable();
    }
  }

  // Clean form
  resetForm(){
    this.formListing.reset();
    this.formReport.reset();
    this.isUpdate = false;
  }

  // Close form
  cancel(){
    this.resetForm();
  }
}
