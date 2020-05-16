import com.example.login.Work


object DataManger{
    val work = mutableListOf<Work>()

    init {
        creatMokData()
    }



    private fun creatMokData(){
        val work1 = Work("emil", "100", "inget")
        work.add(work1)
    }
}