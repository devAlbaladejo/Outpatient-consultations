import { Component, OnInit } from '@angular/core';
import { RoomModel } from 'src/app/model/room-model';
import { RoomService } from 'src/app/services/room.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit{
  listRooms: RoomModel[];
  message: string;

  constructor(private roomService: RoomService, private utilsService: UtilsService) { }

  ngOnInit(): void{
    this.list();
  }

  // List all rooms
  list(){
    this.roomService.getRooms().subscribe(resp => {
      if(resp){
        this.listRooms = resp;
      }
    });
  }

  // Actives a disabled room or disables an activated room
  // @param room: Room
  activedDisableRoom(room: any){

    this.message = room.active == 1 ? "Room disabled" : "Room actived";

    room.active = room.active == 1 ? 0 : 1;

    this.roomService.updateRoom(room.id, room).subscribe(resp => {
      if(resp){
        this.list();
        this.utilsService.showAlert(this.message,"Done");
      }
    });
  }
}
