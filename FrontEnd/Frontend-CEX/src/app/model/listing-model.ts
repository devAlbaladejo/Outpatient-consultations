import { DoctorModel } from "./doctor-model";
import { AssistantModel } from "./assistant-model";
import { RoomModel } from "./room-model";
import { DiaryModel } from "./diary-model";

export class ListingModel{
    id: number;
    doctor: DoctorModel;
    doctorComment: string;
    assistant: AssistantModel;
    assistantComment: string;
    room: RoomModel;
    date: Date;
    schedule: string;
    diary: DiaryModel;
}