package ru.skillbranch.devintensive.models

class Bender(
    var status: Status = Status.NORMAL,
    var question: Question = Question.NAME
) {
    fun askQuestion() : String = question.question

    fun getValidHint() : String = question.validHint

    fun listenAnswer(answer: String) : Pair<String, Triple<Int, Int, Int>> {
        return when {
            question.answers.contains(answer) -> {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            }
            question == Question.IDLE -> "${question.question}" to status.color
            status == Status.CRITICAL -> {
                status = Status.NORMAL
                question = Question.NAME
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }
            else -> {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            }
        }
    }

    fun validation(answer: String) : Boolean {
        return when (question) {
            Question.NAME -> answer.trim().firstOrNull()?.isUpperCase() ?: false
            Question.PROFESSION -> answer.trim().firstOrNull()?.isLowerCase() ?: false
            Question.MATERIAL -> answer.trim().contains(Regex("\\d")).not()
            Question.BDAY -> answer.trim().contains(Regex("^\\d+$"))
            Question.SERIAL -> answer.trim().contains(Regex("^\\d{7}$"))
            Question.IDLE -> true
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>, val validHint: String) {
        NAME("Как меня зовут?", listOf("бендер", "bender"), "Имя должно начинаться с заглавной буквы") {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender"), "Профессия должна начинаться со строчной буквы") {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood"), "Материал не должен содержать цифр") {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993"), "Год моего рождения должен содержать только цифры") {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057"), "Серийный номер содержит только цифры, и их 7") {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf(), "") {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }
}