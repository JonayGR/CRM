export interface Ticket {
    id: number;
    nombre: string;
    description: string;
    fechaInicio: Date;
    flechaFin: Date;
    completed: boolean;
}
