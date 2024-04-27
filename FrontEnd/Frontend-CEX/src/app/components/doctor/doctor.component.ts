import { Component, OnInit } from '@angular/core';
import { DoctorModel } from 'src/app/model/doctor-model';
import { DoctorService } from 'src/app/services/doctor.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SpecialityModel } from 'src/app/model/speciality-model';
import { SpecialityService } from 'src/app/services/speciality.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-doctor',
  templateUrl: './doctor.component.html',
  styleUrls: ['./doctor.component.css']
})
export class DoctorComponent implements OnInit{
  
  listDoctors: DoctorModel[];
  listActiveDoctors: DoctorModel[];
  listDeletedDoctors: DoctorModel[];
  listSpecialities: SpecialityModel[];
  formDoctor: FormGroup;
  isUpdate: boolean = false;

  constructor(private doctorService: DoctorService, private specialityService: SpecialityService, 
    private utilsService: UtilsService) { }

  ngOnInit(): void{
    this.specialityService.getSpecialities().subscribe(speciality => this.listSpecialities = speciality);
    this.list();
    this.formDoctor = new FormGroup({
      id: new FormControl(),
      name: new FormControl('' , Validators.required),
      speciality: new FormControl('', Validators.required),
      active: new FormControl(),
    });
  }

  // List all doctors
  list(){
    this.doctorService.getDoctors().subscribe(resp => {
      if(resp){
        this.listDoctors = resp;
        this.listActiveDoctors = this.listDoctors.filter(e => e.active == 1);
        this.listDeletedDoctors = this.listDoctors.filter(e => e.active == 0);
        this.resetForm();
      }
    });
  }

  // Show and clean Doctor Form
  newDoctor(){
    this.isUpdate = false;
    this.resetForm();
  }

  // Create a doctor
  saveDoctor(){
    this.setSpeciality();
    this.doctorService.saveDoctor(this.formDoctor.value).subscribe(resp => {
      if(resp){
        this.list();
        this.resetForm();
        this.utilsService.showAlert("Doctor created","Done");
      }
    });
  }

  // Update a doctor
  updateDoctor(){
    this.setSpeciality();
    this.doctorService.updateDoctor(this.formDoctor.value.id,this.formDoctor.value).subscribe(resp => {
      if(resp){
        this.list();
        this.resetForm();
        this.utilsService.showAlert("Doctor updated","Done");
      }
    });
  }

  // Delete a doctor
  // @param id: Doctor ID
  deleteDoctor(id: any){
    this.doctorService.deleteDoctor(id).subscribe(resp => {
      if(resp){
        this.list();
        this.utilsService.showAlert("Doctor deleted","Done");
      }
    });
  }

  // Gets info of the selected doctor
  // @param doctor: Doctor selected
  selectedDoctor(doctor: any){
    this.isUpdate = true;
    this.formDoctor.controls['id'].setValue(doctor.id);
    this.formDoctor.controls['name'].setValue(doctor.name);
    this.formDoctor.controls['speciality'].setValue(doctor.speciality.code);
    this.formDoctor.controls['active'].setValue(doctor.active);
  }

  // Establishes the doctor's speciality
  setSpeciality(){
    this.formDoctor.value.speciality = this.listSpecialities.filter(e => e.code == this.formDoctor.value.speciality)[0];
  }

  // Actives a doctor that was deleted
  // @param doctor: Doctor selected
  recoverDoctor(doctor: any){
    this.selectedDoctor(doctor);
    this.formDoctor.controls['active'].setValue(1);
    this.updateDoctor();
    this.isUpdate = false;
  }

  // Clean form
  resetForm(){
    this.formDoctor.reset();
    this.isUpdate = false;
  }

  // Close form
  cancel(){
    this.resetForm();
  }
}
