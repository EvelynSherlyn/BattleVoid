import kukaChiquita from "../assets/sprites/miniPersonajes/KukaChiquita.png";
import kuka from "../assets/sprites/personajes/Kuka.png";
import pacoChiquito from "../assets/sprites/miniPersonajes/PacoChiquito.png";
import paco from "../assets/sprites/personajes/Paco.png";
import dioneChiquita from "../assets/sprites/miniPersonajes/DioneChiquita.png";
import dione from "../assets/sprites/personajes/Dione.png";
import Void from "../assets/elementos/void.png";

export const personajes = [
    {
        id: 0,
        nombre: "",
        fuerza: "",
        destreza: "",
        poder: "",
        img1: Void,
        img2: Void,
        info: ""
    },
    {
        id: 1,
        nombre: "Kuka",
        fuerza: 55,
        destreza: 55,
        poder: 55,
        img1: kukaChiquita,
        img2: kuka,
        info: "Fuerza:55 Destreza:55 Poder:55"
    },
    {
        id: 2,
        nombre: "Paco",
        fuerza: 65,
        destreza: 25,
        poder: 90,
        img1: pacoChiquito,
        img2: paco,
        info: "Fuerza:65 Destreza:25 Poder:90"
    },
    {
        id: 3,
        nombre: "Dione",
        fuerza: 50,
        destreza: 80,
        poder: 60,
        img1: dioneChiquita,
        img2: dione,
        info: "Fuerza:50 Destreza:80 Poder:60"
    }
]