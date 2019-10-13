package io.github.mat3e;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_Id_WELCOME = "Hola";

    @Test
    public void test_preparedGreeting_nullName_returnsGreetingWithFallbackName() throws Exception {

        // given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        //  when
        var result = SUT.prepareGreeting(null, "-1");
        //then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_preparedGreeting_name_returnsGreetingWithName() {
        //given
        var name = "test";
        var SUT = new HelloService();
        //when
        var result = SUT.prepareGreeting(name, "-1");
        //then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_preparedGreeting_nullLang_returnsGreetingWithFALLBACK_Id_LANG() throws Exception {
        // given
        var mockRepository = FALLBACK_LANG_IdRepository();
        var SUT = new HelloService(mockRepository);

        //  when
        var result = SUT.prepareGreeting(null, "abc");

        //then
        assertEquals(FALLBACK_Id_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);

    }

    @Test
    public void test_preparedGreeting_textLang_returnsGreetingWithFALLBACK_Id_LANG() throws Exception {
        // given
        var mockRepository = FALLBACK_LANG_IdRepository();
        var SUT = new HelloService(mockRepository);

        //  when
        var result = SUT.prepareGreeting(null, "abc");

        //then
        assertEquals(FALLBACK_Id_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository FALLBACK_LANG_IdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_Id_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }
}
