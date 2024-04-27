import { RoleModel } from "./role-model";

export class UserModel{
    id: number;
    email: string;
    name: string;
    password: string;
    token: string;
    role: RoleModel;
}