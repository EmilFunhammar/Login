package com.example.login

object DataManger {
    val workList = mutableListOf<Work>()

    init {
        createWorkData()
    }

    fun  createWorkData(){
        var  addwork = Work("grävare", "500", "gräva gropar",
            "berga", "emil", "5554324234", "EMILEMIL")
        workList.add(addwork)

        addwork = Work("pilot", "100", "flyga plan",
            "berga", "emil", "5554324234", "EMILEMIL")
        workList.add(addwork)

        addwork = Work("musiker", "7", "spela musik",
            "berga", "emil", "5554324234", "EMILEMIL")
        workList.add(addwork)

        addwork = Work("elektriker", "1500", "dra kabel",
            "berga", "emil", "5554324234", "EMILEMIL")
        workList.add(addwork)
    }
}