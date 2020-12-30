package examples;

import lang.ArraySeq;
import lang.ISeq;

import java.util.List;

public class SeqExamples {

    public static void main(String[] args) {
        ISeq seq = ArraySeq.of(List.of(10000,20000,30000));
        var o1 = seq.first();
        var o2 = seq.first();
        System.out.println(o1 == o2);

        while (seq.first() != null) {
            System.out.println(seq.first());
            seq = seq.rest();
        }
    }
}
