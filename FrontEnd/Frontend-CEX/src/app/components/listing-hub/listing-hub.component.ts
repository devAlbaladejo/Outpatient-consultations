import { Component, OnInit } from '@angular/core';
import { ListingModel } from 'src/app/model/listing-model';
import { ListingService } from 'src/app/services/listing.service';
import { UtilsService } from 'src/app/services/utils.service';
import { utils } from 'xlsx';

@Component({
  selector: 'app-listing-hub',
  templateUrl: './listing-hub.component.html',
  styleUrls: ['./listing-hub.component.css']
})
export class ListingHUBComponent implements OnInit{

  listListing: ListingModel[];
  listListingToday: ListingModel[]; 
  listSpecialities: Set<string> = new Set<string>();
  orderedSpecialities: string[] = [];
  scheduler: string;
  today: string;

  constructor(private listingService: ListingService, private utilsService: UtilsService) { }

  ngOnInit(): void{
    this.list();
    setTimeout("location.reload()", 30000);
  }

  // List all listings
  list(){
    this.listingService.getListings().subscribe(resp => {
      if(resp){
        this.listListing = resp;
        this.listingToday();
      }
    });
  }

  // Gets all today's listings 
  listingToday(){
    let currentDate: Date = new Date();
    let currentDateString = this.utilsService.convertDate(currentDate);
    this.today = currentDateString;

    if(currentDate.getHours() < 15){
      this.listListingToday = this.listListing.filter(e => e.date.toString() == currentDateString && e.schedule == 'M');
      this.scheduler = "Morning";
    }
    else{
      this.listListingToday = this.listListing.filter(e => e.date.toString() == currentDateString && e.schedule == 'A');
      this.scheduler = "Afternoon";
    }

    this.listListingToday.map(e => this.listSpecialities.add(e.doctor.speciality.name));

    this.listListingToday.sort((a,b) => a.room.id - b.room.id);

    this.orderedSpecialities = Array.from(this.listSpecialities).sort();
  }
}
