public class ExceptionExamplesFirstPart {
    /**
     * Ключевые слова throw и catch могут работать только
     * с объектами типа Throwable
     */

    public static class App1 {
        public static void main(String[] args) throws Throwable {
        }
    }

    public static class App2 {
        public static void main(String[] args) {
            try {
            } catch (Throwable t) {
            }
        }
    }

    public static class App3 {
        public static void main(String[] args) {
            // Error - потомок Throwable
            throw new Error();
        }
    }

    /**
     * Кроме того, throw требуется не-null аргумент,
     * иначе NullPointerException в момент выполнения
     */
    public static class App4 {
        public static void main(String[] args) {
            throw null;
        }
    }

    /**
     * null преобразовывается в инстанс NullPointerException
     * в данном случае рекурсивно бросается исключение пока
     * не вылетит StackOverflowError
     */
    public static class App5 {
        public static void main(String[] args) {
            f(null);
        }

        public static void f(NullPointerException e) {
            try {
                throw e;
            } catch (NullPointerException npe) {
                f(npe);
            }
        }
    }

    /**
     * System.out — buffered-поток вывода,
     * а System.err — нет. Таким образом вывод может быть как таким     *
     */
    public static class App6 {
        public static void main(String[] args) {
            System.out.println("sout");
            throw new Error();
        }
    }
    /**
     * >> RUNTIME ERROR: Exception in thread "main" java.lang.Error
     * >> sout
     */
    /**
     * Так и вот таким (err обогнало out при выводе в консоль)
     */
    public static class App7 {
        public static void main(String[] args) {
            System.out.println("sout");
            throw new Error();
        }
    }
    /**
     * >> sout
     * >> RUNTIME ERROR: Exception in thread "main" java.lang.Error
     */
    /**
     *                       буфер сообщений
     *                     +----------------+
     *                  +->| msg2 msg1 msg0 | --> out
     *                 /   +----------------+        \
     *                /                                 +-> +--------+
     * ВАШЕ ПРИЛОЖЕНИЕ                                      | КОНСОЛЬ|
     *                \                                 +-> +--------+
     *                 \                               /
     *                  +------------------------> err
     *                  нет буфера, сразу печатаем
     * когда Вы пишете в System.err — ваше сообщение тут же
     * выводится на консоль, но когда пишете в System.out,
     * то оно может на какое-то время быть буферизированно.
     * Stacktrace необработанного исключение выводится через
     * System.err, что позволяет им обгонять «обычные» сообщения.
     */
    /**
     * Компилятор требует вернуть результат (или требует молчать)
     * <p>
     * <p>
     * Если в объявлении метода сказано, что он возвращает
     * НЕ void, то компилятор зорко следит, что бы мы вернули
     * экземпляр требуемого типа или экземпляр типа, который
     * можно неявно привести к требуемому
     */
    public static class App8 {
        public double sqr(double arg) { // надо double
            return arg * arg;           // double * double - это double
        }
    }

    public class App9 {
        public double sqr(double arg) { // надо double
            int k = 1;                  // есть int
            return k;                   // можно неявно преобразовать int в double
        }
    }

    // на самом деле, компилятор сгенерирует байт-код для следующих исходников
    public class App10 {
        public double sqr(double arg) { // надо double
            int k = 1;                  // есть int
            return (double) k;          // явное преобразование int в double
        }
    }

    //вот так не пройдет (другой тип)
    public static class App11 {
        public static double sqr(double arg) {
            return 1.0;//"hello!";
        }
    }
    //>> COMPILATION ERROR: Incompatible types.
    // Required: double. Found: java.lang.String

    //Вот так не выйдет — нет возврата
    public static class App12 {
        public static double sqr(double arg) {
        return 1.0;}
    }
    //>> COMPILATION ERROR: Missing return statement

    //и вот так не пройдет (компилятор не может удостовериться,
    // что возврат будет)
    public static class App13 {
        public static double sqr(double arg) {
            if (System.currentTimeMillis() % 2 == 0) {
                return arg * arg; // если currentTimeMillis() - четное число, то все ОК
            }
            // а если нечетное, что нам возвращать?
        return 1.0;}
    }
    //>> COMPILATION ERROR: Missing return statement

    //Компилятор отслеживает, что бы мы что-то вернули, так как
    // иначе непонятно, что должна была бы напечатать данная
    // программа
    public static class App14 {
        public static void main(String[] args) {
            double d = sqr(10.0); // ну, и чему равно d?
            System.out.println(d);
        }

        public static double sqr(double arg) {
            // nothing
        return 1.0;}
    }
    //>> COMPILATION ERROR: Missing return statement

    //Из-забавного, можно ничего не возвращать, а «повесить метод»
    public static class App15 {
        public static double sqr(double arg) {
            while (true) ; // Удивительно, но КОМПИЛИРУЕТСЯ!
        }
    }

    //Тут в d никогда ничего не будет присвоено, так как метод
    // sqr повисает
    public static class App16 {
        public static void main(String[] args) {
            double d = sqr(10.0);  // sqr - навсегда "повиснет
            System.out.println(d);     // и d - НИКОГДА НИЧЕГО НЕ
            // БУДЕТ ПРИСВОЕНО!
        }

        public static double sqr(double arg) {
            while (true) ; // Вот тут мы на века "повисли"
        }
    }

    //Компилятор пропустит «вилку» (таки берем в квадрат ИЛИ висим)
    public static class App17 {
        public static double sqr(double arg) {
            if (System.currentTimeMillis() % 2 == 0) {
                return arg * arg; // ну ладно, вот твой double
            } else {
                while (true) ;     // а тут "виснем" навсегда
            }
        }
    }

    //Но механизм исключений позволяет НИЧЕГО НЕ ВОЗВРАЩАТЬ!
    public static class App18 {
        public static double sqr(double arg) {
            throw new RuntimeException();
        }
    }

    //Итак, у нас есть ТРИ варианта для компилятора
    public static class App19 {
        public static double sqr(double arg) {// согласно объявлению метода ты должен вернуть double
            long time = System.currentTimeMillis();
            if (time % 2 == 0) {
                return arg * arg;      // ок, вот твой double
            } else if (time % 2 == 1) {
                while (true) ;     // не, я решил "повиснуть"
            } else {
                    throw new RuntimeException();
                    // или бросить исключение
            }
            }
        }
        // Но КАКОЙ ЖЕ double вернет функция, бросающая
        // RuntimeException?
        // А НИКАКОЙ!
        public static class App20 {
            public static void main(String[] args) {
                // sqr - "сломается" (из него "выскочит" исключение),
                double d = sqr(10.0);  // выполнение метода
                // main() прервется в этой строчке и
                // d - НИКОГДА НИЧЕГО НЕ БУДЕТ ПРИСВОЕНО!
                System.out.println(d); // и печатать нам
                // ничего не придется!
            }

            public static double sqr(double arg) {
                throw new RuntimeException();
                // "бросаем" исключение
            }
        }
        //>> RUNTIME ERROR: Exception in
        // thread "main" java.lang.RuntimeException
    /**
     * Задача: реализовать функцию, вычисляющую площадь
     * прямоугольника. Важно, что задание звучит именно так,
     * в терминах предметной области — «вычислить площадь
     * прямоугольника», а не в терминах решения «перемножить
     * два числа». Вопрос: что делать, если мы обнаружили, что хотя бы один из аргументов — отрицательное число?
     * Если просто умножить, то мы пропустили ошибочные данные
     * дальше. Что еще хуже, возможно, мы «исправили ситуацию» —
     * сказали что площадь прямоугольника с двумя отрицательными
     * сторонами -10 и -20 = 200.
     */
    /**
     * Но «правильный путь» таков: если обнаружили возможное
     * некорректное поведение, то
     * 1. Вычисления остановить, сгенерировать сообщение-поломку,
     * которое трудно игнорировать, предоставить пользователю
     * информацию о причине, предоставить пользователю
     * возможность все починить (загрузить белье назад и повторно
     * нажать кнопку старт)
     */
    public static int area(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Negative sizes: w = " + width + ", h = " + height);
        }
        return width * height;
    }

    /**
     * Механизм исключительных ситуация (исключений) —
     * это механизм НЕЛОКАЛЬНОЙ ПЕРЕДАЧИ УПРАВЛЕНИЯ.
     * Что под этим имеется в виду?
     * Программа, в ходе своего выполнения (точнее исполнения
     * инструкций в рамках отдельного потока), оперирует
     * стеком («стопкой») фреймов. Передача управления
     * осуществляется либо в рамках одного фрейма
     * Либо передача управления происходит в «стопке» фреймов
     * между СОСЕДНИМИ фреймами
     * вызов метода: создаем новый фрейм, помещаем его на
     * верхушку стека и переходим в него
     * выход из метода: возвращаемся к предыдущему фрейму
     * (через return или просто кончились инструкции в методе)
     */
    /**
     * return — выходим из ОДНОГО фрейма (из фрейма #4(метод h()))
     */
    public static class App21 {
        public static void main(String[] args) {
            System.err.println("#1.in");
            f(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println("#1.out"); // вернулись
        } // выходим из текущего фрейма, кончились инструкции

        public static void f() {
            System.err.println(".   #2.in");
            g(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   #2.out");  //вернулись
        } // выходим из текущего фрейма, кончились инструкции

        public static void g() {
            System.err.println(".   .   #3.in");
            h(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   .   #3.out"); // вернулись
        } // выходим из текущего фрейма, кончились инструкции

        public static void h() {
            System.err.println(".   .   .   #4.in");
            if (true) {
                System.err.println(".   .   .   #4.RETURN");
                return; // выходим из текущего фрейма по 'return'
            }
            System.err.println(".   .   .   #4.out"); // ПРОПУСКАЕМ
        }
    }
    /**
     * >> #1.in
     * >> .   #2.in
     * >> .   .   #3.in
     * >> .   .   .   #4.in
     * >> .   .   .   #4.RETURN
     * >> .   .   #3.out
     * >> .   #2.out
     * >> #1.out
     *
     * throw — выходим из ВСЕХ фреймов
     */
    public static class App22 {
        public static void main(String[] args) {
            System.err.println("#1.in");
            f(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println("#1.out"); // ПРОПУСТИЛИ!
        }

        public static void f() {
            System.err.println(".   #2.in");
            g(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   #2.out"); // ПРОПУСТИЛИ!
        }

        public static void g() {
            System.err.println(".   .   #3.in");
            h(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
        }

        public static void h() {
            System.err.println(".   .   .   #4.in");
            if (true) {
                System.err.println(".   .   .   #4.THROW");
                throw new Error();
                // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
            }
            System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
        }
    }
    /**
     * >> #1.in
     * >> .   #2.in
     * >> .   .   #3.in
     * >> .   .   .   #4.in
     * >> .   .   .   #4.THROW
     * >> RUNTIME ERROR: Exception in thread "main" java.lang.Error
     *
     * При помощи catch мы можем остановить летящее исключение
     * (причина, по которой мы автоматически покидаем фреймы).
     * Останавливаем через 3 фрейма, пролетаем фрейм
     * #4(метод h()) + пролетаем фрейм #3(метод g())
     * + фрейм #2(метод f())
     */
    public static class App23 {
        public static void main(String[] args) {
            System.err.println("#1.in");
            try {
                f(); // создаем фрейм, помещаем в стек, передаем в него управление
            } catch (Error e) { // "перехватили" "летящее" исключение
                System.err.println("#1.CATCH");  // и работаем
            }
            System.err.println("#1.out");  // работаем дальше
        }

        public static void f() {
            System.err.println(".   #2.in");
            g(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   #2.out"); // ПРОПУСТИЛИ!
        }

        public static void g() {
            System.err.println(".   .   #3.in");
            h(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
        }

        public static void h() {
            System.err.println(".   .   .   #4.in");
            if (true) {
                System.err.println(".   .   .   #4.THROW");
                throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
            }
            System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
        }
    }
    /**
     * >> #1.in
     * >> .   #2.in
     * >> .   .   #3.in
     * >> .   .   .   #4.in
     * >> .   .   .   #4.THROW
     * >> #1.CATCH
     * >> #1.out
     *
     * Обратите внимание, стандартный сценарий работы был
     * восстановлен в методе main() (фрейм #1)
     *
     * Останавливаем через 2 фрейма, пролетаем
     * фрейм #4(метод h()) + пролетаем фрейм #3(метод g())
     */
    public static class App24 {
        public static void main(String[] args) {
            System.err.println("#1.in");
            f(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println("#1.out"); // вернулись и работаем
        }

        public static void f() {
            System.err.println(".   #2.in");
            try {
                g(); // создаем фрейм, помещаем в стек, передаем в него управление
            } catch (Error e) { // "перехватили" "летящее" исключение
                System.err.println(".   #2.CATCH");  // и работаем
            }
            System.err.println(".   #2.out");  // работаем дальше
        }

        public static void g() {
            System.err.println(".   .   #3.in");
            h(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   .   #3.out"); // ПРОПУСТИЛИ!
        }

        public static void h() {
            System.err.println(".   .   .   #4.in");
            if (true) {
                System.err.println(".   .   .   #4.THROW");
                throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
            }
            System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
        }
    }
    /**
     * >> #1.in
     * >> .   #2.in
     * >> .   .   #3.in
     * >> .   .   .   #4.in
     * >> .   .   .   #4.THROW
     * >> .   #2.CATCH
     * >> .   #2.out
     * >> #1.out
     *
     * Останавливаем через 1 фрейм (фактически аналог
     * return, просто покинули фрейм «другим образом»)
     */
    public static class App25 {
        public static void main(String[] args) {
            System.err.println("#1.in");
            f(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println("#1.out"); // вернулись и работаем
        }

        public static void f() {
            System.err.println(".   #2.in");
            g(); // создаем фрейм, помещаем в стек, передаем в него управление
            System.err.println(".   #2.out"); // вернулись и работаем
        }

        public static void g() {
            System.err.println(".   .   #3.in");
            try {
                h(); // создаем фрейм, помещаем в стек, передаем в него управление
            } catch (Error e) { // "перехватили" "летящее" исключение
                System.err.println(".   .   #3.CATCH");  // и работаем
            }
            System.err.println(".   .   #3.out");  // работаем дальше
        }

        public static void h() {
            System.err.println(".   .   .   #4.in");
            if (true) {
                System.err.println(".   .   .   #4.THROW");
                throw new Error(); // выходим со всей пачки фреймов ("раскрутка стека") по 'throw'
            }
            System.err.println(".   .   .   #4.out"); // ПРОПУСТИЛИ!
        }
    }
    /**
     * >> #1.in
     * >> .   #2.in
     * >> .   .   #3.in
     * >> .   .   .   #4.in
     * >> .   .   .   #4.THROW
     * >> .   .   #3.CATCH
     * >> .   .   #3.out
     * >> .   #2.out
     * >> #1.out
     */
/**
 *                     Object
 *                       |
 *                   Throwable
 *                   /      \
 *               Error     Exception
 *                             |
 *                     RuntimeException
 *
 * То, что исключения являются объектами важно для нас в
 * двух моментах
 * 1. Они образуют иерархию с корнем java.lang.Throwable
 * (java.lang.Object — предок java.lang.Throwable,
 * но Object — уже не исключение)
 * 2. Они могут иметь поля и методы (в этой статье это
 * не будем использовать)
 *
 * По первому пункту: catch — полиморфная конструкция,
 * т.е. catch по типу Parent перехватывает летящие экземпляры
 * любого типа, который является Parent-ом (т.е. экземпляры
 * непосредственно Parent-а или любого потомка Parent-а)
 */
    public static class App26 {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
            if (true) {throw new RuntimeException();}
            System.err.print(" 1");
            } catch (Exception e) { // catch по Exception ПЕРЕХВАТЫВАЕТ RuntimeException
                System.err.print(" 2");
            }
            System.err.println(" 3");
        }
    }
    /**
     * >> 0 2 3
     */
    /**
     * catch по потомку не может поймать предка
     */
    public static class App27 {
        public static void main(String[] args) throws Exception { // пока игнорируйте 'throws'
            try {
                System.err.print(" 0");
                if (true) {throw new Exception();}
                System.err.print(" 1");
            } catch (RuntimeException e) {
                System.err.print(" 2");
            }
            System.err.print(" 3");
        }
    }
    /**
     * >> 0
     * >> RUNTIME EXCEPTION: Exception in thread
     * "main" java.lang.Exception
     */
    /**
     * catch по одному «брату» не может поймать другого
     * «брата» (Error и Exception не находятся в отношении
     * предок-потомок, они из параллельных веток наследования
     * от Throwable)
     */

    public static class App28 {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new Error();}
                System.err.print(" 1");
            } catch (Exception e) {
                System.err.print(" 2");
            }
            System.err.print(" 3");
        }
    }
    /**
     * >> 0
     * >> RUNTIME EXCEPTION: Exception in
     * thread "main" java.lang.Error
     */
    /**
     * По предыдущим примерам — надеюсь вы обратили внимание,
     * что если исключение перехвачено, то JVM выполняет
     * операторы идущие ПОСЛЕ последних скобок try+catch.
     * Но если не перехвачено, то мы
     * 1. не заходим в блок catch
     * 2. покидаем фрейм метода с летящим исключением
     *
     * А что будет, если мы зашли в catch, и потом бросили
     * исключение ИЗ catch?
     */
    public static class App29 {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new RuntimeException();}
                System.err.print(" 1");
            } catch (RuntimeException e) {     // перехватили RuntimeException
                System.err.print(" 2");
                if (true) {throw new Error();} // но бросили Error
            }
            System.err.println(" 3");          // пропускаем - уже летит Error
        }
    }
    /**
     * >> 0 2
     * >> RUNTIME EXCEPTION: Exception in thread
     * "main" java.lang.Error
     */
    /**
     * Мы можем даже кинуть тот объект, что у нас есть «на руках»
     */
    public static class App30 {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new RuntimeException();}
                System.err.print(" 1");
            } catch (RuntimeException e) { // перехватили RuntimeException
                System.err.print(" 2");
                if (true) {throw e;}       // и бросили ВТОРОЙ раз ЕГО ЖЕ
            }
            System.err.println(" 3");      // пропускаем - опять летит RuntimeException
        }
    }
    /**
     * >> 0 2
     * >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.RuntimeException
     */
    /**
     * И мы не попадем в другие секции catch, если они есть
     */
    public static class App31 {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new RuntimeException();}
                System.err.print(" 1");
            } catch (RuntimeException e) {     // перехватили RuntimeException
                System.err.print(" 2");
                if (true) {throw new Error();} // и бросили новый Error
            } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
                System.err.print(" 3");
            }
            System.err.println(" 4");
        }
    }
    /**
     * >> 0 2
     * >> RUNTIME EXCEPTION: Exception in thread "main" java.lang.Error
     * Обратите внимание, мы не напечатали «3», хотя у нас летит Error
     * а «ниже» расположен catch по Error. Но важный момент в том, что
     * catch имеет отношение исключительно к try-секции, но не к другим
     * catch-секциям.
     *
     * Как покажем ниже — можно строить вложенные конструкции, но вот
     * пример, «исправляющий» эту ситуацию
     */
    public static class App32 {
        public static void main(String[] args) {
            try {
                System.err.print(" 0");
                if (true) {throw new RuntimeException();}
                System.err.print(" 1");
            } catch (RuntimeException e) { // перехватили RuntimeException
                System.err.print(" 2.1");
                try {
                    System.err.print(" 2.2");
                    if (true) {throw new Error();} // и бросили новый Error
                    System.err.print(" 2.3");
                } catch (Throwable t) {            // перехватили Error
                    System.err.print(" 2.4");
                }
                System.err.print(" 2.5");
            } catch (Error e) { // хотя есть cath по Error "ниже", но мы в него не попадаем
                System.err.print(" 3");
            }
            System.err.println(" 4");
        }
    }
    /**
     * >> 0 2.1 2.2 2.4 2.5 4
     */
    /**
     * Как вы видели, мы можем расположить несколько catch после одного try.
     *
     * Но есть такое правило — нельзя ставить потомка после предка!
     * (RuntimeException после Exception)
     */
    public static class App33 {
        public static void main(String[] args) {
            try {
            } catch (Exception e) {
            } //catch (RuntimeException e) {
            }
        }

    /**
     * >> COMPILATION ERROR: Exception 'java.lang.RuntimeException' has alredy been caught
     */
    /**
     * Ставить брата после брата — можно (RuntimeException после Error)
     */
    public static class App34 {
        public static void main(String[] args) {
            try {
            } catch (Error e) {
            } catch (RuntimeException e) {
            }
        }
    }

    /**
     * >> catch Exception
     * >> next statement
     *
     * Как происходит выбор «правильного» catch? Да очень просто —
     * JVM идет сверху-вниз до тех пор, пока не найдет такой catch
     * что в нем указано ваше исключение или его предок — туда и
     * заходит. Ниже — не идет.
     *
     * Выбор catch осуществляется в runtime (а не в compile-time),
     * значит учитывается не тип ССЫЛКИ (Throwable), а тип ССЫЛАЕМОГО
     * (Exception)
     */
    public static class App35 {
        public static void main(String[] args) {
            try {
                Throwable t = new Exception(); // ссылка типа Throwable указывает на объект типа Exception
                throw t;
            } catch (RuntimeException e) {
                System.err.println("catch RuntimeException");
            } catch (Exception e) {
                System.err.println("catch Exception");
            } catch (Throwable e) {
                System.err.println("catch Throwable");
            }
            System.err.println("next statement");
        }
    }
    /**
     * >> catch Exception
     * >> next statement
     */
    /**
     * finally-секция получает управление, если try-блок завершился успешно
     */
    public static class App36 {
        public static void main(String[] args) {
            try {
                System.err.println("try");
            } finally {
                System.err.println("finally");
            }
        }
    }
    /**
     * finally-секция получает управление, даже если try-блок завершился исключением
     */
    public static class App37 {
        public static void main(String[] args) {
            try {
                throw new RuntimeException();
            } finally {
                System.err.println("finally");
            }
        }
    }
    /**
     * >> finally
     * >> Exception in thread "main" java.lang.RuntimeException
     */
    /**
     * finally-секция получает управление, даже если try-блок завершился
     * директивой выхода из метода
     */
    public static class App38 {
        public static void main(String[] args) {
            try {
                return;
            } finally {
                System.err.println("finally");
            }
        }
    }
    /**
     * >> finally
     */
    /**
     * finally-секция НЕ вызывается только если мы «прибили» JVM
     */
    public static class App39 {
        public static void main(String[] args) {
            try {
                System.exit(42);
            } finally {
                System.err.println("finally");
            }
        }
    }
    /**
     * >> Process finished with exit code 42
     */
    /**
     * System.exit(42) и Runtime.getRuntime().exit(42) — это синонимы
     */
    public static class App40 {
        public static void main(String[] args) {
            try {
                Runtime.getRuntime().exit(42);
            } finally {
                System.err.println("finally");
            }
        }
    }
    /**
     * >> Process finished with exit code 42
     *
     * И при Runtime.getRuntime().halt(42) — тоже не успевает зайти в finally
     */
    public static class App41 {
        public static void main(String[] args) {
            try {
                Runtime.getRuntime().halt(42);
            } finally {
                System.err.println("finally");
            }
        }
    }
    /**
     * >> Process finished with exit code 42
     *
     * exit() vs halt()
     * javadoc: java.lang.Runtime#halt(int status)
     * … Unlike the exit method, this method does not cause shutdown hooks
     * to be started and does not run uninvoked finalizers if
     * finalization-on-exit has been enabled. If the shutdown sequence has
     * already been initiated then this method does not wait for any running
     * shutdown hooks or finalizers to finish their work.
     *
     * Однако finally-секция не может «починить» try-блок завершившийся
     * исключение (заметьте, «more» — не выводится в консоль)
     */
    public static class App42 {
        public static void main(String[] args) {
            try {
                System.err.println("try");
                if (true) {throw new RuntimeException();}
            } finally {
                System.err.println("finally");
            }
            System.err.println("more");
        }
    }
    /**
     * >> try
     * >> finally
     * >> Exception in thread "main" java.lang.RuntimeException
     *
     * Трюк с «if (true) {...}» требуется, так как иначе компилятор
     * обнаруживает недостижимый код (последняя строка) и отказывается
     * его компилировать
     */
    public static class App43 {
        public static void main(String[] args) {
            try {
                System.err.println("try");
                throw new RuntimeException();
            } finally {
                System.err.println("finally");
            }
            //System.err.println("more");
        }
    }
    /**
     * >> COMPILER ERROR: Unrechable statement
     */
    /**
     * И finally-секция не может «предотвратить» выход
     * из метода, если try-блок вызвал return («more» —
     * не выводится в консоль)
     */
    public static class App44 {
        public static void main(String[] args) {
            try {
                System.err.println("try");
                if (true) {
                    return;
                }
            } finally {
                System.err.println("finally");
            }
            System.err.println("more");
        }
    }}
    /**
     * >> try
     * >> finally
     *
     * Однако finally-секция может «перебить» throw/return
     * при помощи другого throw/return
     *
     * finally-секция может быть использована для завершающего
     * действия, которое гарантированно будет вызвано (даже
     * если было брошено исключение или автор использовал
     * return) по окончании работы
     *
     * Например для освобождения захваченной блокировки
     * Или для закрытия открытого файлового потока
     *
     * Не рекомендуемые практики
     *
     * — return из finally-секции (можем затереть исключение
     * из try-блока)
     * — действия в finally-секции, которые могут бросить
     * исключение (можем затереть исключение из try-блока)
     *
     * try-cacth-finally допускает неограниченное вложение с
     * выполнение всех блоков finally
     */







