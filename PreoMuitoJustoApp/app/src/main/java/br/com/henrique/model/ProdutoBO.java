package br.com.henrique.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ProdutoBO {
    private String valorParcela;
    private String valorTotal;
    private String valorJuros;

    public static String verifyDiscount(Double juros, boolean entrada) {
        Double desconto = entrada ? juros - 5.0 : juros + 5.0;
        return desconto < 0.0 ? "0" : desconto.toString();
    }

    public static ProdutoBO calcular(Produto produto) {
        ProdutoBO bo = new ProdutoBO();
        if (produto.getJuros() < 0) {
            produto.setJuros(0.0);
        }
        Double valorJuros = (produto.getJuros() / 100) * produto.getValor();
        Double valorTotal = valorJuros + produto.getValor();
        Double valorParcela = valorTotal / produto.getQuantidadeParcelas();
        bo.setValorJuros(formatTextPrice(valorJuros.toString()));
        bo.setValorParcela(formatTextPrice(valorParcela.toString()));
        bo.setValorTotal(formatTextPrice(valorTotal.toString()));
        return bo;
    }

    public static String getCurrencySymbol() {
        return NumberFormat.getCurrencyInstance(Locale.getDefault()).getCurrency().getSymbol();
    }

    public static boolean isValidNumber(String string) {
        return convertStringToBigDecimal(string) != null;
    }

    public static BigDecimal convertStringToBigDecimal(String string) {
        String replaceable = String.format("[%s,.\\s]", getCurrencySymbol());
        String cleanString = string.replaceAll(replaceable, "");
        try {
            return new BigDecimal(cleanString).setScale(
                    2, BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
        } catch (NumberFormatException e) {
            return null;

        }
    }

    public static String formatPrice(String price) {
        DecimalFormat df = new DecimalFormat("0.00");
        return String.valueOf(df.format(Double.valueOf(price)));

    }

    public static String formatTextPrice(String price) {
        BigDecimal bD = new BigDecimal(formatPriceSave(formatPrice(price)));
        String newFormat = String.valueOf(NumberFormat.getCurrencyInstance(Locale.getDefault()).format(bD));
        String replaceable = String.format("[%s]", getCurrencySymbol());
        return newFormat.replaceAll(replaceable, "");
    }

    static String formatPriceSave(String price) {
        String replaceable = String.format("[%s,.\\s]", getCurrencySymbol());
        String cleanString = price.replaceAll(replaceable, "");
        StringBuilder stringBuilder = new StringBuilder(cleanString.replaceAll(" ", ""));
        return String.valueOf(stringBuilder.insert(cleanString.length() - 2, '.'));
    }

    public static boolean isValidString(String string) {
        return string != null || !string.trim().isEmpty();
    }

    public String getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(String valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(String valorJuros) {
        this.valorJuros = valorJuros;
    }
}
