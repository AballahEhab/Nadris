package com.example.android.nadris.ui.studentActivity.quizzes

object setData {

    const val name:String="name"
    const val score:String="score"

    fun getQuestion():ArrayList<QuestionData>{
        var que:ArrayList<QuestionData> = arrayListOf()
        var question1 = QuestionData(
            1,
            "قال الشاعر:\n" +
                    "إذا كنت في نعمة فارعها فإن المعاصي تزيل النعم\n"+
            "ميز مما يلي المحل اإلعرابي لجملة \"فارعها \" في الشطر األول.",

            "لا محل لها من اإلعراب.",
            " في محل جر نعت.",
            " في محل جزم جواب شرط",
            "في محل نصب خبر كان",
            4
        )
        var question2 = QuestionData(
            2,
            "\"إن ت رد محبة الناس فلن معهم تنل حبهم\".\n" +
                    "بين مما يلي سبب اقتران جواب الشرط بالفاء في الجملة السابقة.",

            "طلبية",
            "منفية",
            " مبدوءة بجامد",
            "اسمية",
            1
        )
        var question3 = QuestionData(
            3,
            "قال الشاعر:\n" +
                    "لمن تطلب الدنيا إذا لم ت رد بها سرور مح ِّب أو َمساءة مجرم\n" +
                    "بين المصدر الميمي الوارد في البيت السابق",

            "مساءة.",
            "محب.",
            "سرور",
            "مجرم",
            2
        )
        var question4 = QuestionData(
            4,
            "من قتل محمد علي ؟",

            "مش عارف",
            "ايش عرفني",
            "لما هكبر هعرف",
            "ابلغ عن أقرب الناس ليا",
            3
        )

        var question5 = QuestionData(
            5,
            "عارف حاجة في المنهج؟",

            "لا",
            "لا قوي",
            "بكره اصحابي",
            "بحب اصحابي قوي",
            4
        )

        que.add(question1)
        que.add(question2)
        que.add(question3)
        que.add(question4)
        que.add(question5)
        return que
    }
}