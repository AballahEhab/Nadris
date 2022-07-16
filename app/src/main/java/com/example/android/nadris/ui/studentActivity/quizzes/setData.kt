package com.example.android.nadris.ui.studentActivity.quizzes

import com.example.android.nadris.ui.teacherActivity.quiz_teacher.QuizData

object setData {

    fun getQuestion():MutableList<QuizData>{
        var que:ArrayList<QuizData> = arrayListOf()
        var question1 = QuizData(
            1,
            "قال الشاعر:\n" +
                    "إذا كنت في نعمة فارعها فإن المعاصي تزيل النعم\n"+
                    "ميز مما يلي المحل اإلعرابي لجملة \"فارعها \" في الشطر األول.",

            answer = mutableListOf("لا محل لها من اإلعراب.",
                " في محل جر نعت.",
                " في محل جزم جواب شرط",
                "في محل نصب خبر كان"),
            mutableListOf(1,2),
            " في محل جزم جواب شرط",

            )
        var question2 = QuizData(
            2,
            "\"إن ت رد محبة الناس فلن معهم تنل حبهم\".\n" +
                    "بين مما يلي سبب اقتران جواب الشرط بالفاء في الجملة السابقة.",

            answer = mutableListOf("طلبية",
                "منفية",
                " مبدوءة بجامد",
                "اسمية"),
            mutableListOf(2),
            " مبدوءة بجامد",
        )
        var question3 = QuizData(
            3,
            "قال الشاعر:\n" +
                    "لمن تطلب الدنيا إذا لم ت رد بها سرور مح ِّب أو َمساءة مجرم\n" +
                    "بين المصدر الميمي الوارد في البيت السابق",

            answer = mutableListOf("مساءة.",
                "محب.",
                "سرور",
                "مجرم"),
            mutableListOf(1,4),
            "سرور",
        )
        var question4 = QuizData(
            4,
            "من قتل محمد علي ؟",

            answer = mutableListOf("مش عارف",
                "ايش عرفني",
                "لما هكبر هعرف",
                "ابلغ عن أقرب الناس ليا"),
            mutableListOf(4),
            "لما هكبر هعرف",
        )

        var question5 = QuizData(
            5,
            "عارف حاجة في المنهج؟",

            answer = mutableListOf("لا",
                "لا قوي",
                "بكره اصحابي",
                "بحب اصحابي قوي"),
            mutableListOf(1),
            "بكره اصحابي",
        )

        que.add(question1)
        que.add(question2)
        que.add(question3)
        que.add(question4)
        que.add(question5)
        return que
    }
}