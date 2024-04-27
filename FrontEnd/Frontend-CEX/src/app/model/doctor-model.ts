import { SpecialityModel } from "./speciality-model";

export class DoctorModel{
    id: number;
    name: string;
    speciality: SpecialityModel;
    active: number;
}