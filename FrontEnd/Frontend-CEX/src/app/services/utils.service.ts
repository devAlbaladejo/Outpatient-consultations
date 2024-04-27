import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor(private MatSnackbar: MatSnackBar) { }

  // Pop up to show a message in screen
  showAlert(message: string, type: string){
    this.MatSnackbar.open(message, type, {
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      duration:5000
    });
  }

  // Convert a date to string
  convertDate(date: Date){
    return date.getFullYear() + "-" + ((date.getMonth()+1) < 10 ? "0" + (date.getMonth()+1) : (date.getMonth()+1)) + "-" + (date.getDate() < 10 ? "0" + date.getDate() : date.getDate());
  }
}
