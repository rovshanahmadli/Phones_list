package com.rovshanahmadli.notes_retrofit

import com.google.gson.annotations.SerializedName

//In this data class: we are listing which (data)parameters we will receive from the Server.
data class ProductModel(
    @SerializedName("id")   // It says that, one variable will come with the name of "id" and assign it to the id.
    val id : String,        // Name of variables in the Model class(data class) needs to be the same as response inside the json(in URL).
    @SerializedName("name") // We can set the different name but we need to add the SerializedName annotation on top of the variable.
    val name : String       //If the name of variable is the same with the name of variable in the json, there is no need to write @SerializedName.
)
