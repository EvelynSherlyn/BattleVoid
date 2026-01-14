import React from "react";

export default function Sprites({ jugador, ide, clase }) {
    return (
        <div>
            <img src={jugador.img2} alt="Personaje" id={ide} className={clase} />
        </div>
    )
}