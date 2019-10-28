package com.aries.eagle.design.pattern.proxy.static_proxy;

/**
 * @author Kristin
 * @since 2018/7/27 10:47
 **/
public interface Printable {
    String getPrinterName();

    void setPrinterName(String name);

    void print(String message);
}
