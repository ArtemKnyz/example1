import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Example1 {
    public static void main(String args[]) throws Exception{

        List<Current> current = new ArrayList<>();
        Document doc = Jsoup.connect("http://www.cbr.ru/scripts/XML_daily.asp").get();
        Elements elements = doc.getElementsByAttributeValue("ID", "R01200");
        for (Element elementName: elements) {
            String nameCurrent = elementName.child(3).text();
            String value = elementName.child(4).text();

            current.add(new Current(nameCurrent, value));
        }

        System.out.println("Курс гонконского доллара к российскому рублю: " + current);
    }
}

class Current {
    private String currency;
    private String value;

    public Current(String nameCurrent, String value) {
        this.currency = nameCurrent;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public String getValue() {
        return value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Current current = (Current) o;
        return Objects.equals(currency, current.currency) &&
                Objects.equals(value, current.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, value);
    }

    @Override
    public String toString() {
        return "1$ = " + value + "rub.";
    }
}
