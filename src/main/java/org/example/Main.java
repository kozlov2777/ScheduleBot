package org.example;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main extends TelegramLongPollingBot {
    private LocalDate localDate =LocalDate.now();

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());


    }

    @Override
    public String getBotUsername() {
        return "ScheduleNUOSBot";
    }

    @Override
    public String getBotToken() {
        return "5841349905:AAFoFdD4DaVcgKrWGUEIdYsFszTkQo77uPI";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);
        if(update.hasMessage() && update.getMessage().getText().equals("/start")){
            SendMessage message = createMessage("Привіт, що бажаєш отримати?");
            message.setChatId(chatId);
            attachButtons(message, Map.of("Розклад","Розклад","Розклад на неділю","Розклад на неділю",
                    "Розклад на завтра","Розклад на завтра"));
            sendApiMethodAsync(message);
        }
        if(update.hasCallbackQuery()){
            if (update.getCallbackQuery().getData().equals("Розклад на завтра")){
                if (localDate.getDayOfWeek().name().equals("MONDAY")) {
                    scheduleOfNextDay(chatId,"TUESDAY");
                }
                if (localDate.getDayOfWeek().name().equals("TUESDAY")) {
                    scheduleOfNextDay(chatId,"WEDNESDAY");
                }
                if (localDate.getDayOfWeek().name().equals("WEDNESDAY")) {
                    scheduleOfNextDay(chatId,"THURSDAY");
                }
                if (localDate.getDayOfWeek().name().equals("THURSDAY")) {
                    scheduleOfNextDay(chatId, "FRIDAY");
                }
                if (localDate.getDayOfWeek().name().equals("FRIDAY")) {
                    scheduleOfNextDay(chatId,"MONDAY");
                }
            }
        }
        if(update.hasCallbackQuery()){
            if(update.getCallbackQuery().getData().equals("Розклад")){
                if (localDate.getDayOfWeek().name().equals("MONDAY")) {
                    scheduleDayOfWeek(chatId);
                }
                if (localDate.getDayOfWeek().name().equals("TUESDAY")) {
                    scheduleDayOfWeek(chatId);
                }
                if (localDate.getDayOfWeek().name().equals("WEDNESDAY")) {
                    scheduleDayOfWeek(chatId);
                }
                if (localDate.getDayOfWeek().name().equals("THURSDAY")) {
                    scheduleDayOfWeek(chatId);
                }
                if (localDate.getDayOfWeek().name().equals("FRIDAY")) {
                    scheduleDayOfWeek(chatId);
                }
            }
        }
        if(update.hasCallbackQuery()){
            if(update.getCallbackQuery().getData().equals("Розклад на неділю")){
                    SendMessage message = createMessage("Тиждень над рискою\n" +
                            "Понеділок\n" +
                            "1) Логічне програмування Лекція (9.00-10.20 доц Гайдаенко ОВ)\n" +
                            "2) Логічне програмування Практика  (10.30-11.50 доц Гайдаенко ОВ)   \n" +
                            "3)  Політологія Лекція (12.20-13.40 доц Шаповалова ІВ) \n" +
                            "4) Організація баз даних і знань Лабораторна робота (13.50-15.10  доц Книрік НР,  Книрік КО)\n" +
                            "\n" +
                            "Вівторок\n" +
                            "\n" +
                            "Середа\n" +
                            "1) Операційні системи UNIX Лабораторна робота (9.00-10.20 доц Гайда АЮ, Суслов ОС)\n" +
                            "2) Комп мережі Лекція (10.30-11.50 доц Партас ВК)\n" +
                            "3) Основи теорії і методів Лекція (12.20-13.40 ст в Морозова ГС)\n" +
                            "\n" +
                            "Четверг\n" +
                            "1) Операційні системи UNIX Лекція (9.00-10.20  доц Гайда АЮ)   \n" +
                            "3) ВК Ораторське мистецтво/Основи медіаграмотності Лекція (12.20-13.40  проф Філатова ОС/доц Гінкевич ОВ)   \n" +
                            "4)  ВК3 Комп системи мультимедіа Лекція (13.50-15.10  ст в Маршак ОІ)\n" +
                            "5) ВК3 Комп системи мультимедіа Практика (15.20-16.40  ст в Маршак ОІ)\n" +
                            "\n" +
                            "П’ятниця\n" +
                            "1)  Комп мережі(1підгр) Лабораторна робота (9.00-10.20  доц Партас ВК) \n" +
                            "2) Комп мережі(1підгр)/Основ теор і метод...(2підгр) Лабораторна робота (10.30-11.50  доц Партас ВК/в Баковська ІВ) \n" +
                            "3) Компют мережі(2підгр)/Осн теор в метод...(1підгр) Лабораторна робота (12.20-13.40  доц Партас ВК/в Баковська ІВ) \n" +
                            "4)  Комп мережі (2підгр) Лабораторна робота (13.50-15.10 доц Партас ВК)\n" +
                            "\n" +
                            "Тиждень під рискою\n" +
                            "Понеділок\n" +
                            "2) ВК3 Логічне програмув Практика (10.30-11.50  доц Гайдаєнко ОВ)  \n" +
                            "3) Організація баз даних і знань Лекція (12.20-13.40 доц Книрік НР)  \n" +
                            "4) Організація баз даних і знань Лабораторна робота (13.50-15.10  доц Книрік НР  в Книрік КО) \n" +
                            "\n" +
                            "Вівторок\n" +
                            "\n" +
                            "Середа\n" +
                            "1) Операційні системи UNIX  Лабораторна робота (9.00-10.20  доц Гайда АЮ  в Суслов ОС)  \n" +
                            "2) Комп мережі  Лекція (10.30-11.50  доц Партас ВК)  \n" +
                            "3) Основи теор і метод оптим...  Лекція  (12.20-13.40 ст в Морозова ГС) \n" +
                            "4) Політологія Практика (13.50-15.10   доц Шаповалова ІВ)\n" +
                            "\n" +
                            "Четверг\n" +
                            "3) ВК Ораторське мистецтво/Основи медіаграмотності  Практика  (12.20-13.40 проф Філатова ОС/доц Гінкевич ОВ)  \n" +
                            "4) ВК3 Комп сист мультимед  Практика  (13.50-15.10  ст в Маршак ОІ)\n" +
                            "\n" +
                            "П’ятниця    \n" +
                            "1) Комп мережі(1підгр) Лабораторна робота (9.00-10.20 доц Партас ВК)  \n" +
                            "2)  Комп мережі(1підгр)/Осн теор в мето..(2підгр)  Лабораторна робота (10.30-11.50  доц Партас ВК/в Баковська ІВ) \n" +
                            "3) Ком мережі(2підгр)/Осн теор...(1підгр) Лабораторна робота (12.20-13.40 доц ПартасВК/в Баковська ІВ)  \n" +
                            "4) Комп мережі(2підгр)  Лабораторна робота  (13.50-15.10  доц Партас ВК)");
                    message.setChatId(chatId);
                    sendApiMethodAsync(message);

            }
        }

    }
    public void scheduleOfNextDay(Long chatId, String day){
        SendMessage message = createMessage(scheduleMap().get(day));
        message.setChatId(chatId);
        sendApiMethodAsync(message);
    }
    public void scheduleDayOfWeek(Long chatId){
        for (Map.Entry<String, String> entry:scheduleMap().entrySet()) {
            if (LocalDate.now().getDayOfWeek().name().equals(entry.getKey())){
                SendMessage message = createMessage(entry.getValue());
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }
        }
    }

    public Map<String ,String> scheduleMap(){
        Map<String,String> map = new HashMap<>();
        map.put("MONDAY","1) Логічне програмування Лекція (9.00-10.20 доц Гайдаенко ОВ)\n" +
                "2) Логічне програмування Практика  (10.30-11.50 доц Гайдаенко ОВ)   \n" +
                "3)  Політологія Лекція (12.20-13.40 доц Шаповалова ІВ) \n" +
                "4) Організація баз даних і знань Лабораторна робота (13.50-15.10  доц Книрік НР,  Книрік КО)");
        map.put("TUESDAY","Free Day");
        map.put("WEDNESDAY","1) Операційні системи UNIX Лабораторна робота (9.00-10.20 доц Гайда АЮ, Суслов ОС)\n" +
                "2) Комп мережі Лекція (10.30-11.50 доц Партас ВК)\n" +
                "3) Основи теорії і методів Лекція (12.20-13.40 ст в Морозова ГС)");
        map.put("THURSDAY","1) Операційні системи UNIX Лекція (9.00-10.20  доц Гайда АЮ)   \n" +
                "3) ВК Ораторське мистецтво/Основи медіаграмотності Лекція (12.20-13.40  проф Філатова ОС/доц Гінкевич ОВ)   \n" +
                "4)  ВК3 Комп системи мультимедіа Лекція (13.50-15.10  ст в Маршак ОІ)\n" +
                "5) ВК3 Комп системи мультимедіа Практика (15.20-16.40  ст в Маршак ОІ)");
        map.put("FRIDAY","1)  Комп мережі(1підгр) Лабораторна робота (9.00-10.20  доц Партас ВК) \n" +
                "2) Комп мережі(1підгр)/Основ теор і метод...(2підгр) Лабораторна робота (10.30-11.50  доц Партас ВК/в Баковська ІВ) \n" +
                "3) Компют мережі(2підгр)/Осн теор в метод...(1підгр) Лабораторна робота (12.20-13.40  доц Партас ВК/в Баковська ІВ) \n" +
                "4)  Комп мережі (2підгр) Лабораторна робота (13.50-15.10 доц Партас ВК)");

        return map;
    }
    public Long getChatId(Update update){
        if(update.hasMessage()){
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()){
            return update. getCallbackQuery().getFrom().getId();
        }
        return null;
    }
    public SendMessage createMessage(String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        sendMessage.setParseMode("markdown");
        return sendMessage;
    }
    public void  attachButtons(SendMessage message, Map<String,String> buttons){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (String buttonName : buttons.keySet()) {
            String buttonValue = buttons.get(buttonName);
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
            button.setCallbackData(buttonValue);
            keyboard.add(Arrays.asList(button));
        }
        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }
}