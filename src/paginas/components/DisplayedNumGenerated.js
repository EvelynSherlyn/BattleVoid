import React from "react";

export default function DisplayedNumGenerated( {icon, power, ide} ) {
  
        return (
            <div id={ide}>
                <img src={icon} alt="Void" />
                <p className={"noMuevas"}>{power === 0 ? "??" : power}</p>
            </div>
        )

}