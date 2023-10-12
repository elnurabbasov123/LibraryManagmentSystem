package utility;

import enums.Exception;
import exception.GeneralException;

import java.util.List;
import java.util.function.Predicate;

public class Searcher {

     public static <T> T search(List<T> list, Predicate<T> field) {
          T t=null;
          boolean ok = false;
          for (T element : list) {
               if (field.test(element)) {
                    t=element;
                    ok = true;
               }

          }
          if (ok) {
               return t;
          } else {
               throw new GeneralException(Exception.OBJECT_NOT_FOUND_EXCEPTION);
          }
     }
}


