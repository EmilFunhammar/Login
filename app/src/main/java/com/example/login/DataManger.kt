package com.example.login

object DataManger {
    val workList = mutableListOf<Work>()

    init {
        createWorkData()
    }

    fun  createWorkData(){
        var  addwork = Work("grävare", "500", "gräva gropar")
        workList.add(addwork)
        addwork = Work("pilot", "100", "flyga plan")
        workList.add(addwork)
        addwork = Work("musiker", "7", "spela musik")
        workList.add(addwork)
        addwork = Work("elektriker", "1500", "dra kabel")
        workList.add(addwork)
        addwork = Work("programmerare", "780", "programmera")
        workList.add(addwork)
        addwork = Work("läkare", "10066", "läka")
        workList.add(addwork)
        addwork = Work("polis", "550", "sjkuta")
        workList.add(addwork)

    }

}