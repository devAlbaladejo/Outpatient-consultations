import { Component, OnInit } from '@angular/core';
import { AssistantModel } from 'src/app/model/assistant-model';
import { AssistantService } from 'src/app/services/assistant.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-assistant',
  templateUrl: './assistant.component.html',
  styleUrls: ['./assistant.component.css']
})
export class AssistantComponent implements OnInit{

  listAssistants: AssistantModel[];
  listActiveAssistants: AssistantModel[];
  listDeletedAssistants: AssistantModel[];
  formAssistant: FormGroup;
  isUpdate: boolean = false;

  constructor(private assistantService: AssistantService, private utilsService: UtilsService) { }

  ngOnInit(): void{
    this.list();
    this.formAssistant = new FormGroup({
      id: new FormControl(),
      name: new FormControl('', Validators.required),
      color: new FormControl('#000000'),
      active: new FormControl(),
    })
  }

  // List all assistants
  list(){
    this.assistantService.getAssistants().subscribe(resp => {
      if(resp){
        this.listAssistants = resp;
        this.listActiveAssistants = this.listAssistants.filter(e => e.active == 1);
        this.listDeletedAssistants = this.listAssistants.filter(e => e.active == 0);
      }
    });
  }

  // Show and clean Assistant Form
  newAssistant(){
    this.isUpdate = false;
    this.resetForm();
  }

  // Create an assistant
  saveAssistant(){
    this.assistantService.saveAssistant(this.formAssistant.value).subscribe(resp => {
      if(resp){
        this.list();
        this.resetForm();
        this.utilsService.showAlert("Assistant created","Done");
      }
    });
  }

  // Update an assistant
  updateAssistant(){
    this.assistantService.updateAssistant(this.formAssistant.value.id,this.formAssistant.value).subscribe(resp => {
      if(resp){
        this.list();
        this.resetForm();
        this.utilsService.showAlert("Assistant updated","Done");
      }
    });
  }

  // Delete an assistant
  // @param id: Assistant ID
  deleteAssistant(id: any){
    this.assistantService.deleteAssistant(id).subscribe(resp => {
      if(resp){
        this.list();
        this.utilsService.showAlert("Assistant deleted","Done");
      }
    });
  }

  // Gets info of the selected assistant
  // @param assistant: Assistant selected
  selectedAssistant(assistant: any){
    this.isUpdate = true;
    this.formAssistant.controls['id'].setValue(assistant.id);
    this.formAssistant.controls['name'].setValue(assistant.name);
    this.formAssistant.controls['color'].setValue(assistant.color);
    this.formAssistant.controls['active'].setValue(assistant.active);
  }

  // Actives a assistant that was deleted
  // @param assistant: Assistant selected
  recoverAssistant(assistant: any){
    this.selectedAssistant(assistant);
    this.formAssistant.controls['active'].setValue(1);
    this.updateAssistant();
    this.isUpdate = false;
  }

  // Clean form
  resetForm(){
    this.formAssistant.reset();
    this.isUpdate = false;
    this.formAssistant.controls['color'].setValue("#000000");
  }

  // Close form
  cancel(){
    this.resetForm();
  }
}
