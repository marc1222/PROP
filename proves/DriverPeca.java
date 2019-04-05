public class DriverPeca {
    public void testGetNextId() {
        System.out.println("Get Next Id");
        System.out.println(Peca.getNextId());
    }

    public void testGetId() {
        System.out.println("Get Id");
        StubReina reina = new StubReina();
        System.out.println(reina.getId());
    }

    public void testGetColor() {
        System.out.println("Get Color");
        StubReina reina = new StubReina();
        System.out.println(reina.getColor());
        StubReina reina2 = new StubReina(define.WHITE);
        System.out.println(reina2.getColor());
        StubReina reina3 = new StubReina(define.BLACK);
        System.out.println(reina3.getColor());
    }

    public void testSetId() {
        System.out.println("Set Id");
        StubReina reina = new StubReina();
        reina.setId(47);
        System.out.println(reina.id);
    }

    public void testSetColor() {
        System.out.println("Set Color");
        StubReina reina = new StubReina();
        reina.setColor(define.BLACK);
        System.out.println(reina.color);
    }

    public static void main (String[] args) {
        DriverPeca dp = new DriverPeca();
        dp.testGetNextId();
        dp.testGetId();
        dp.testGetColor();
        dp.testSetId();
        dp.testSetColor();
    }
}
