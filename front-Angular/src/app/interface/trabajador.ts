import { Ticket } from './ticket';
export interface Trabajador {
    id: number;
    nombre: string;
    apellidos1: string;
    apellidos2: string;
    correo: string;
    telefono: string;
    ticket: Ticket[];
}
