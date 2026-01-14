import React from "react";

export default function ProfileIcon( {icon, num, cambiar} ) {
    return (
        <img src={require("../assets/sprites/iconos/"+icon+".png")} onClick={() => cambiar(num)} />
    )
}