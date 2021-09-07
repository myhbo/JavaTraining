import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionExamplesSecondPart {
    /**
     * — Throwable и Exception и все их наследники (за исключением
     * наследников Error-а и RuntimeException-а) — checked
     * — Error и RuntimeException и все их наследники — unchecked
     *
     * checked exception = проверяемое исключение, проверяемое
     * компилятором.
     *                      Object
     *                       |
     *                Throwable(CHECKED)
     *                /            \
     *      Error(UNCHECKED)    Exception(CHECKED)
     *                             |
     *                   RuntimeException(UNCHECKED)
     *
     * Я называю связь между проверяемыми исключениями и
     * throws — «пессимистичной», польку мы можем «напугать»
     * о большем, чем может произойти на самом деле,
     * но не наоборот
     *
     * Мы не можем бросать, но не предупредить
     */

     public static class App1 {
         public static void main(String[] args) {
              //throw new Exception(); // тут ошибка компиляции
         }
     }
    /**
     * Мы не можем бросать, но предупредить о «меньшем»
     */
    public static class App2 {
        public static void main(String[] args) throws IOException {
            //throw new Exception(); // тут ошибка компиляции
        }
    }
    /**
     * Мы можем предупредить точно о том, что бросаем
     */
    public static class App3 {
        public static void main(String[] args) throws Exception { // предупреждаем о Exception
            throw new Exception(); // и кидаем Exception
        }
    }
    /**
     * Мы можем предупредить о большем, чем мы бросаем
     */
    public static class App4 {
        public static void main(String[] args) throws Throwable {
            // предупреждаем "целом" Throwable
            throw new Exception(); // а кидаем только Exception
        }
    }
    /**
     * Можем даже предупредить о том, чего вообще нет
     * Даже если предупреждаем о том, чего нет — все обязаны бояться
     */
    public static class App5 {
        public static void main(String[] args) {
            //f(); // тут ошибка компиляции
        }

        public static void f() throws Exception {
        }
    }
    /**
     * >> COMPILATION ERROR: unhandled exception: java.lang.Exception
     */
    /**
     * Вызов метода, который «пугает» unchecked исключением не
     * накладывает на нас никаких обязанностей.
     */
    public static class App6 {
        public static void main(String[] args) {
            f();
        }
        public static void f() throws RuntimeException {
        }
    }
    /**
     * Эта конструкция служит цели «указать» программисту-читателю
     * кода на то, что ваш метод может выбросить некоторое
     * непроверяемое (unchecked) исключение.
     *
     * Пример (java.lang.NumberFormatException — непроверяемое
     * исключение):
     */
    /**
     * Множественные исключения
     *
     * Рассмотрим ситуацию с кодом, который может бросать проверяемые исключения разных типов.
     * Далее учитывайте, что EOFException и FileNotFoundException — потомки IOException.
     *
     * Мы можем точно указать, что выбрасываем
     */
    public static class App7 {
        // пугаем ОБОИМИ исключениями
        public static void main(String[] args) throws EOFException, FileNotFoundException {
            if (System.currentTimeMillis() % 2 == 0) {
                throw new EOFException();
            } else {
                throw new FileNotFoundException();
            }
        }
    }
    public static class App8 {
        // пугаем ОБОИМИ исключениями
        public static void main(String[] args) throws EOFException, FileNotFoundException {
            f0();
            f1();
        }
        public static void f0() throws EOFException {}
        public static void f1() throws FileNotFoundException {}
    }
    /**
     * А можем «испугать» сильнее (предком обоих исключений)
     */
    public static class App9 {
        // пугаем ПРЕДКОМ исключений
        public static void main(String[] args) throws IOException {
            if (System.currentTimeMillis() % 2 == 0) {
                throw new EOFException();
            } else {
                throw new FileNotFoundException();
            }
        }
    }
    /**
     * Можно и вот так: EOFException и FileNotFoundException
     * «обобщаем до» IOException, а InterruptedException
     * «пропускаем нетронутым» (InterruptedException —
     * НЕ потомок IOException)
     */
    public static class App10 {
        public static void main(String[] args) throws IOException, InterruptedException {
            f0();
            f1();
            f2();
        }
        public static void f0() throws EOFException {}
        public static void f1() throws FileNotFoundException {}
        public static void f2() throws InterruptedException {}
    }
    /**
     * Если вы часть перехватили, то можете этим не пугать
     */
    public static class App11 {
        // EOFException перехватили catch-ом, им не пугаем
        public static void main(String[] args) throws FileNotFoundException {
            try {
                if (System.currentTimeMillis() % 2 == 0) {
                    throw new EOFException();
                } else {
                    throw new FileNotFoundException();
                }
            } catch (EOFException e) {
                // ...
            }
        }
    }
    /**
     * Необходимо понимать, что
     * — проверка на cheched исключения происходит в момент
     * компиляции (compile-time checking)
     * — перехват исключений (catch) происходит в момент выполнения
     * (runtime checking)
     */
    public static class App12 {
        // пугаем Exception
        public static void main(String[] args) throws Exception {
            Throwable t = new Exception(); // и лететь будет Exception
            //throw t; // но тут ошибка компиляции
        }
    }
    /**
     * >> COMPILATION ERROR: unhandled exception: java.lang.Throwable
     */
    /**
     * Полная аналогия с
     */
    public static class App13 {
        public static void main(String[] args) throws Exception {
            Object ref = "Hello!";  // ref указывает на строку
            //char c = ref.charAt(0); // но тут ошибка компиляции
        }
    }

    /**
     * >> COMPILATION ERROR: Cannot resolve method 'charAt(int)'     *
     */
    /**
     * Компилятор не пропустит этот код, хотя метод main
     * ГАРАНТИРОВАННО НЕ ВЫБРОСИТ ИСКЛЮЧЕНИЯ
     */
    public static class App14 {
        // пугаем Exception
        public static void main(String[] args) throws Exception {
            try {
                Throwable t = new Exception();
                // и лететь будет Exception
                //throw t; // но тут ошибка компиляции
            } catch (Exception e) {
                System.out.println("Перехвачено!");
            }
        }
    }
    /**
     * Overriding и throws
     *
     * При переопределении (overriding) список исключений
     * потомка не обязан совпадать с таковым у предка.
     * Но он должен быть «не сильнее» списка предка:
     */
    public static class Parent {
        // предок пугает IOException и InterruptedException
        public void f() throws IOException, InterruptedException {}
    }

    static class Child extends Parent {
        // а потомок пугает только потомком IOException
        @Override
        public void f() throws FileNotFoundException {}
    }
    /**
     * Однако тут мы попытались «расширить тип» бросаемых исключений
     */
    public static class Parent1 {
        public void f() throws IOException, InterruptedException {}
    }

    static class ChildB extends Parent1 {
        @Override
        public void f() /*throws Exception*/ {}
    }
    /**
     * Логика расположения свойства НЕ СВЯЗАНА С НАСЛЕДОВАНИЕМ. Эту логику мы рассмотрим позже (в следующих статьях).
     * Однако свойство checked/unchecked пользовательских классов исключений строится ИСКЛЮЧИТЕЛЬНО НА ОСНОВЕ НАСЛЕДОВАНИЯ.
     * Правило крайне простое:
     * 1. Если исключение из списка Throwable, Error, Exception, RuntimeException — то твое свойство надо просто запомнить.
     * 2. Если ты не из списка, то твое свойство равно свойству предка. Нарушить наследование тут нельзя.
     *
     * Если мы породим потомков A, B, C, D, E, F, G, H, I, J, K, L которые следующим образом наследуются от «корневища» (Throwable, Error, Exception, RuntimeException), то значение их свойства checked/unchecked можно увидеть на схеме
     *                     Object
     *                       |
     *                Throwable(CHECKED)
     *                /    |       \
     *  Error(UNCHECKED)   |   Exception(CHECKED)
     *     |       |       |      |            |
     *     A(UNC)  D(UNC)  |      F(C)        RuntimeException(UNCHECKED)
     *   /   \             |     /   \             |       |
     * B(UNC) C(UNC)       |   G(C)  H(C)          I(UNC)  J(UNC)
     *                    E(C)                   /   \
     *                                        K(UNC) L(UNC)
     */



}



