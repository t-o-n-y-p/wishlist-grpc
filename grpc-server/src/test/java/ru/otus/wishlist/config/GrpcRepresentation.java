package ru.otus.wishlist.config;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import org.assertj.core.presentation.StandardRepresentation;

public class GrpcRepresentation extends StandardRepresentation {

  private final JsonFormat.Printer jsonPrinter = JsonFormat.printer();

  @Override
  public String toStringOf(Object object) {
    if (object instanceof MessageOrBuilder m) {
      try {
        return jsonPrinter.print(m);
      } catch (InvalidProtocolBufferException e) {
        return "/* unknown */";
      }
    }
    return super.toStringOf(object);
  }
}
