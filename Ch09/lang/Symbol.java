package lang;

public class Symbol {
    final String ns;
    final String name;
    transient String _str;

    private Symbol(String ns, String name) {
        this.ns = ns;
        this.name = name;
    }

    public static Symbol of(String ns, String name){
        return new Symbol(ns, name);
    }

    public static Symbol of(String nsname){
        int i = nsname.indexOf('/');
        if(i == -1 || nsname.equals("/"))
            return new Symbol(null, nsname);
        else
            return new Symbol(nsname.substring(0, i), nsname.substring(i + 1));
    }

    public String toString(){
        if (_str == null) {
            if (ns != null) {
                _str = (ns + "/" + name);
            } else {
                _str = name;
            }
        }
        return _str;
    }

}
