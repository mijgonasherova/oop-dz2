import java.util.ArrayList;
import java.util.List;


public class Market implements MarketBehaviour, QueueBehaviour {
    private final List<Actor> queue;


    public Market() {
        this.queue = new ArrayList<>();
    }
    @Override
    public void acceptToMarket(Actor actor) {
        System.out.println(actor.getName() + " пришел в магазин");
        takeInQueue(actor); // Добавляем актера в очередь
    }
    @Override
    public void takeInQueue(Actor actor) {
        System.out.println(actor.getName() + " встал в очередь");
        this.queue.add(actor); // Добавляем актера в список очереди
    }
    @Override
    public void takeOrders() {
        for (Actor actor : queue) {
            if (!actor.isMakeOrder()) {
                actor.setMakeOrder(true); // Устанавливаем статус заказа
                System.out.println(actor.getName() + " сделал свой заказ");
            }
        }
    }
    @Override
    public void giveOrders() {
        for (Actor actor : queue) {
            if (actor.isMakeOrder()) {
                actor.setTakeOrder(true); // Устанавливаем статус получения заказа
                System.out.println(actor.getName() + " получил свой заказ");
            }
        }
    }
    @Override
    public void releaseFromQueue() {
        List<Actor> releasedActors = new ArrayList<>();
        for (Actor actor : queue) {
            if (actor.isTakeOrder()) {
                releasedActors.add(actor); // Добавляем актера для освобождения
                System.out.println(actor.getName() + " вышел из очереди и готов уходить");
            }
        }
        releaseFromMarket(releasedActors); // Освобождаем актера из магазина
    }
    @Override
    public void releaseFromMarket(List<Actor> actors) {
        for (Actor actor : actors) {
            System.out.println(actor.getName() + " вышел из магазина");
            queue.remove(actor); // Удаляем актера из очереди
        }
    }
    @Override
    public void update() {
        takeOrders();    // Принимаем заказы
        giveOrders();    // Отдаем заказы
        releaseFromQueue(); // Освобождаем людей из очереди
    }
}


