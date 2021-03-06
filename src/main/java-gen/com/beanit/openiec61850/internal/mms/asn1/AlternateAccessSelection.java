/** This class file was automatically generated by jASN1 (http://www.beanit.com) */
package com.beanit.openiec61850.internal.mms.asn1;

import com.beanit.jasn1.ber.BerLength;
import com.beanit.jasn1.ber.BerTag;
import com.beanit.jasn1.ber.ReverseByteArrayOutputStream;
import com.beanit.jasn1.ber.types.BerNull;
import com.beanit.jasn1.ber.types.BerType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class AlternateAccessSelection implements BerType, Serializable {

  private static final long serialVersionUID = 1L;

  public byte[] code = null;
  private SelectAlternateAccess selectAlternateAccess = null;
  private SelectAccess selectAccess = null;

  public AlternateAccessSelection() {}

  public AlternateAccessSelection(byte[] code) {
    this.code = code;
  }

  public SelectAlternateAccess getSelectAlternateAccess() {
    return selectAlternateAccess;
  }

  public void setSelectAlternateAccess(SelectAlternateAccess selectAlternateAccess) {
    this.selectAlternateAccess = selectAlternateAccess;
  }

  public SelectAccess getSelectAccess() {
    return selectAccess;
  }

  public void setSelectAccess(SelectAccess selectAccess) {
    this.selectAccess = selectAccess;
  }

  public int encode(OutputStream reverseOS) throws IOException {

    if (code != null) {
      for (int i = code.length - 1; i >= 0; i--) {
        reverseOS.write(code[i]);
      }
      return code.length;
    }

    int codeLength = 0;
    if (selectAccess != null) {
      codeLength += selectAccess.encode(reverseOS);
      return codeLength;
    }

    if (selectAlternateAccess != null) {
      codeLength += selectAlternateAccess.encode(reverseOS, false);
      // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
      reverseOS.write(0xA0);
      codeLength += 1;
      return codeLength;
    }

    throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
  }

  public int decode(InputStream is) throws IOException {
    return decode(is, null);
  }

  public int decode(InputStream is, BerTag berTag) throws IOException {

    int codeLength = 0;
    BerTag passedTag = berTag;

    if (berTag == null) {
      berTag = new BerTag();
      codeLength += berTag.decode(is);
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
      selectAlternateAccess = new SelectAlternateAccess();
      codeLength += selectAlternateAccess.decode(is, false);
      return codeLength;
    }

    selectAccess = new SelectAccess();
    int choiceDecodeLength = selectAccess.decode(is, berTag);
    if (choiceDecodeLength != 0) {
      return codeLength + choiceDecodeLength;
    } else {
      selectAccess = null;
    }

    if (passedTag != null) {
      return 0;
    }

    throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
  }

  public void encodeAndSave(int encodingSizeGuess) throws IOException {
    ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
    encode(reverseOS);
    code = reverseOS.getArray();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    appendAsString(sb, 0);
    return sb.toString();
  }

  public void appendAsString(StringBuilder sb, int indentLevel) {

    if (selectAlternateAccess != null) {
      sb.append("selectAlternateAccess: ");
      selectAlternateAccess.appendAsString(sb, indentLevel + 1);
      return;
    }

    if (selectAccess != null) {
      sb.append("selectAccess: ");
      selectAccess.appendAsString(sb, indentLevel + 1);
      return;
    }

    sb.append("<none>");
  }

  public static class SelectAlternateAccess implements BerType, Serializable {

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
    private static final long serialVersionUID = 1L;
    public byte[] code = null;
    private AccessSelection accessSelection = null;
    private AlternateAccess alternateAccess = null;

    public SelectAlternateAccess() {}

    public SelectAlternateAccess(byte[] code) {
      this.code = code;
    }

    public AccessSelection getAccessSelection() {
      return accessSelection;
    }

    public void setAccessSelection(AccessSelection accessSelection) {
      this.accessSelection = accessSelection;
    }

    public AlternateAccess getAlternateAccess() {
      return alternateAccess;
    }

    public void setAlternateAccess(AlternateAccess alternateAccess) {
      this.alternateAccess = alternateAccess;
    }

    public int encode(OutputStream reverseOS) throws IOException {
      return encode(reverseOS, true);
    }

    public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

      if (code != null) {
        for (int i = code.length - 1; i >= 0; i--) {
          reverseOS.write(code[i]);
        }
        if (withTag) {
          return tag.encode(reverseOS) + code.length;
        }
        return code.length;
      }

      int codeLength = 0;
      codeLength += alternateAccess.encode(reverseOS, true);

      codeLength += accessSelection.encode(reverseOS);

      codeLength += BerLength.encodeLength(reverseOS, codeLength);

      if (withTag) {
        codeLength += tag.encode(reverseOS);
      }

      return codeLength;
    }

    public int decode(InputStream is) throws IOException {
      return decode(is, true);
    }

    public int decode(InputStream is, boolean withTag) throws IOException {
      int codeLength = 0;
      int subCodeLength = 0;
      BerTag berTag = new BerTag();

      if (withTag) {
        codeLength += tag.decodeAndCheck(is);
      }

      BerLength length = new BerLength();
      codeLength += length.decode(is);

      int totalLength = length.val;
      codeLength += totalLength;

      subCodeLength += berTag.decode(is);
      accessSelection = new AccessSelection();
      subCodeLength += accessSelection.decode(is, berTag);
      subCodeLength += berTag.decode(is);

      if (berTag.equals(AlternateAccess.tag)) {
        alternateAccess = new AlternateAccess();
        subCodeLength += alternateAccess.decode(is, false);
        if (subCodeLength == totalLength) {
          return codeLength;
        }
      }
      throw new IOException(
          "Unexpected end of sequence, length tag: "
              + totalLength
              + ", actual sequence length: "
              + subCodeLength);
    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
      ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
      encode(reverseOS, false);
      code = reverseOS.getArray();
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      appendAsString(sb, 0);
      return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

      sb.append("{");
      sb.append("\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      if (accessSelection != null) {
        sb.append("accessSelection: ");
        accessSelection.appendAsString(sb, indentLevel + 1);
      } else {
        sb.append("accessSelection: <empty-required-field>");
      }

      sb.append(",\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      if (alternateAccess != null) {
        sb.append("alternateAccess: ");
        alternateAccess.appendAsString(sb, indentLevel + 1);
      } else {
        sb.append("alternateAccess: <empty-required-field>");
      }

      sb.append("\n");
      for (int i = 0; i < indentLevel; i++) {
        sb.append("\t");
      }
      sb.append("}");
    }

    public static class AccessSelection implements BerType, Serializable {

      private static final long serialVersionUID = 1L;

      public byte[] code = null;
      private Component component = null;
      private Unsigned32 index = null;
      private IndexRange indexRange = null;
      private BerNull allElements = null;

      public AccessSelection() {}

      public AccessSelection(byte[] code) {
        this.code = code;
      }

      public Component getComponent() {
        return component;
      }

      public void setComponent(Component component) {
        this.component = component;
      }

      public Unsigned32 getIndex() {
        return index;
      }

      public void setIndex(Unsigned32 index) {
        this.index = index;
      }

      public IndexRange getIndexRange() {
        return indexRange;
      }

      public void setIndexRange(IndexRange indexRange) {
        this.indexRange = indexRange;
      }

      public BerNull getAllElements() {
        return allElements;
      }

      public void setAllElements(BerNull allElements) {
        this.allElements = allElements;
      }

      public int encode(OutputStream reverseOS) throws IOException {

        if (code != null) {
          for (int i = code.length - 1; i >= 0; i--) {
            reverseOS.write(code[i]);
          }
          return code.length;
        }

        int codeLength = 0;
        int sublength;

        if (allElements != null) {
          codeLength += allElements.encode(reverseOS, false);
          // write tag: CONTEXT_CLASS, PRIMITIVE, 3
          reverseOS.write(0x83);
          codeLength += 1;
          return codeLength;
        }

        if (indexRange != null) {
          codeLength += indexRange.encode(reverseOS, false);
          // write tag: CONTEXT_CLASS, CONSTRUCTED, 2
          reverseOS.write(0xA2);
          codeLength += 1;
          return codeLength;
        }

        if (index != null) {
          codeLength += index.encode(reverseOS, false);
          // write tag: CONTEXT_CLASS, PRIMITIVE, 1
          reverseOS.write(0x81);
          codeLength += 1;
          return codeLength;
        }

        if (component != null) {
          sublength = component.encode(reverseOS);
          codeLength += sublength;
          codeLength += BerLength.encodeLength(reverseOS, sublength);
          // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
          reverseOS.write(0xA0);
          codeLength += 1;
          return codeLength;
        }

        throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
      }

      public int decode(InputStream is) throws IOException {
        return decode(is, null);
      }

      public int decode(InputStream is, BerTag berTag) throws IOException {

        int codeLength = 0;
        BerTag passedTag = berTag;

        if (berTag == null) {
          berTag = new BerTag();
          codeLength += berTag.decode(is);
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
          codeLength += BerLength.skip(is);
          component = new Component();
          codeLength += component.decode(is, null);
          return codeLength;
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
          index = new Unsigned32();
          codeLength += index.decode(is, false);
          return codeLength;
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
          indexRange = new IndexRange();
          codeLength += indexRange.decode(is, false);
          return codeLength;
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 3)) {
          allElements = new BerNull();
          codeLength += allElements.decode(is, false);
          return codeLength;
        }

        if (passedTag != null) {
          return 0;
        }

        throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
      }

      public void encodeAndSave(int encodingSizeGuess) throws IOException {
        ReverseByteArrayOutputStream reverseOS =
            new ReverseByteArrayOutputStream(encodingSizeGuess);
        encode(reverseOS);
        code = reverseOS.getArray();
      }

      public String toString() {
        StringBuilder sb = new StringBuilder();
        appendAsString(sb, 0);
        return sb.toString();
      }

      public void appendAsString(StringBuilder sb, int indentLevel) {

        if (component != null) {
          sb.append("component: ");
          component.appendAsString(sb, indentLevel + 1);
          return;
        }

        if (index != null) {
          sb.append("index: ").append(index);
          return;
        }

        if (indexRange != null) {
          sb.append("indexRange: ");
          indexRange.appendAsString(sb, indentLevel + 1);
          return;
        }

        if (allElements != null) {
          sb.append("allElements: ").append(allElements);
          return;
        }

        sb.append("<none>");
      }

      public static class Component implements BerType, Serializable {

        private static final long serialVersionUID = 1L;

        public byte[] code = null;
        private BasicIdentifier basic = null;

        public Component() {}

        public Component(byte[] code) {
          this.code = code;
        }

        public BasicIdentifier getBasic() {
          return basic;
        }

        public void setBasic(BasicIdentifier basic) {
          this.basic = basic;
        }

        public int encode(OutputStream reverseOS) throws IOException {

          if (code != null) {
            for (int i = code.length - 1; i >= 0; i--) {
              reverseOS.write(code[i]);
            }
            return code.length;
          }

          int codeLength = 0;
          if (basic != null) {
            codeLength += basic.encode(reverseOS, true);
            return codeLength;
          }

          throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
        }

        public int decode(InputStream is) throws IOException {
          return decode(is, null);
        }

        public int decode(InputStream is, BerTag berTag) throws IOException {

          int codeLength = 0;
          BerTag passedTag = berTag;

          if (berTag == null) {
            berTag = new BerTag();
            codeLength += berTag.decode(is);
          }

          if (berTag.equals(BasicIdentifier.tag)) {
            basic = new BasicIdentifier();
            codeLength += basic.decode(is, false);
            return codeLength;
          }

          if (passedTag != null) {
            return 0;
          }

          throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
        }

        public void encodeAndSave(int encodingSizeGuess) throws IOException {
          ReverseByteArrayOutputStream reverseOS =
              new ReverseByteArrayOutputStream(encodingSizeGuess);
          encode(reverseOS);
          code = reverseOS.getArray();
        }

        public String toString() {
          StringBuilder sb = new StringBuilder();
          appendAsString(sb, 0);
          return sb.toString();
        }

        public void appendAsString(StringBuilder sb, int indentLevel) {

          if (basic != null) {
            sb.append("basic: ").append(basic);
            return;
          }

          sb.append("<none>");
        }
      }

      public static class IndexRange implements BerType, Serializable {

        public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
        private static final long serialVersionUID = 1L;
        public byte[] code = null;
        private Unsigned32 lowIndex = null;
        private Unsigned32 numberOfElements = null;

        public IndexRange() {}

        public IndexRange(byte[] code) {
          this.code = code;
        }

        public Unsigned32 getLowIndex() {
          return lowIndex;
        }

        public void setLowIndex(Unsigned32 lowIndex) {
          this.lowIndex = lowIndex;
        }

        public Unsigned32 getNumberOfElements() {
          return numberOfElements;
        }

        public void setNumberOfElements(Unsigned32 numberOfElements) {
          this.numberOfElements = numberOfElements;
        }

        public int encode(OutputStream reverseOS) throws IOException {
          return encode(reverseOS, true);
        }

        public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

          if (code != null) {
            for (int i = code.length - 1; i >= 0; i--) {
              reverseOS.write(code[i]);
            }
            if (withTag) {
              return tag.encode(reverseOS) + code.length;
            }
            return code.length;
          }

          int codeLength = 0;
          codeLength += numberOfElements.encode(reverseOS, false);
          // write tag: CONTEXT_CLASS, PRIMITIVE, 1
          reverseOS.write(0x81);
          codeLength += 1;

          codeLength += lowIndex.encode(reverseOS, false);
          // write tag: CONTEXT_CLASS, PRIMITIVE, 0
          reverseOS.write(0x80);
          codeLength += 1;

          codeLength += BerLength.encodeLength(reverseOS, codeLength);

          if (withTag) {
            codeLength += tag.encode(reverseOS);
          }

          return codeLength;
        }

        public int decode(InputStream is) throws IOException {
          return decode(is, true);
        }

        public int decode(InputStream is, boolean withTag) throws IOException {
          int codeLength = 0;
          int subCodeLength = 0;
          BerTag berTag = new BerTag();

          if (withTag) {
            codeLength += tag.decodeAndCheck(is);
          }

          BerLength length = new BerLength();
          codeLength += length.decode(is);

          int totalLength = length.val;
          codeLength += totalLength;

          subCodeLength += berTag.decode(is);
          if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
            lowIndex = new Unsigned32();
            subCodeLength += lowIndex.decode(is, false);
            subCodeLength += berTag.decode(is);
          } else {
            throw new IOException("Tag does not match the mandatory sequence element tag.");
          }

          if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
            numberOfElements = new Unsigned32();
            subCodeLength += numberOfElements.decode(is, false);
            if (subCodeLength == totalLength) {
              return codeLength;
            }
          }
          throw new IOException(
              "Unexpected end of sequence, length tag: "
                  + totalLength
                  + ", actual sequence length: "
                  + subCodeLength);
        }

        public void encodeAndSave(int encodingSizeGuess) throws IOException {
          ReverseByteArrayOutputStream reverseOS =
              new ReverseByteArrayOutputStream(encodingSizeGuess);
          encode(reverseOS, false);
          code = reverseOS.getArray();
        }

        public String toString() {
          StringBuilder sb = new StringBuilder();
          appendAsString(sb, 0);
          return sb.toString();
        }

        public void appendAsString(StringBuilder sb, int indentLevel) {

          sb.append("{");
          sb.append("\n");
          for (int i = 0; i < indentLevel + 1; i++) {
            sb.append("\t");
          }
          if (lowIndex != null) {
            sb.append("lowIndex: ").append(lowIndex);
          } else {
            sb.append("lowIndex: <empty-required-field>");
          }

          sb.append(",\n");
          for (int i = 0; i < indentLevel + 1; i++) {
            sb.append("\t");
          }
          if (numberOfElements != null) {
            sb.append("numberOfElements: ").append(numberOfElements);
          } else {
            sb.append("numberOfElements: <empty-required-field>");
          }

          sb.append("\n");
          for (int i = 0; i < indentLevel; i++) {
            sb.append("\t");
          }
          sb.append("}");
        }
      }
    }
  }

  public static class SelectAccess implements BerType, Serializable {

    private static final long serialVersionUID = 1L;

    public byte[] code = null;
    private Component component = null;
    private Unsigned32 index = null;
    private IndexRange indexRange = null;
    private BerNull allElements = null;

    public SelectAccess() {}

    public SelectAccess(byte[] code) {
      this.code = code;
    }

    public Component getComponent() {
      return component;
    }

    public void setComponent(Component component) {
      this.component = component;
    }

    public Unsigned32 getIndex() {
      return index;
    }

    public void setIndex(Unsigned32 index) {
      this.index = index;
    }

    public IndexRange getIndexRange() {
      return indexRange;
    }

    public void setIndexRange(IndexRange indexRange) {
      this.indexRange = indexRange;
    }

    public BerNull getAllElements() {
      return allElements;
    }

    public void setAllElements(BerNull allElements) {
      this.allElements = allElements;
    }

    public int encode(OutputStream reverseOS) throws IOException {

      if (code != null) {
        for (int i = code.length - 1; i >= 0; i--) {
          reverseOS.write(code[i]);
        }
        return code.length;
      }

      int codeLength = 0;
      int sublength;

      if (allElements != null) {
        codeLength += allElements.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 4
        reverseOS.write(0x84);
        codeLength += 1;
        return codeLength;
      }

      if (indexRange != null) {
        codeLength += indexRange.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, CONSTRUCTED, 3
        reverseOS.write(0xA3);
        codeLength += 1;
        return codeLength;
      }

      if (index != null) {
        codeLength += index.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 2
        reverseOS.write(0x82);
        codeLength += 1;
        return codeLength;
      }

      if (component != null) {
        sublength = component.encode(reverseOS);
        codeLength += sublength;
        codeLength += BerLength.encodeLength(reverseOS, sublength);
        // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
        reverseOS.write(0xA1);
        codeLength += 1;
        return codeLength;
      }

      throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
    }

    public int decode(InputStream is) throws IOException {
      return decode(is, null);
    }

    public int decode(InputStream is, BerTag berTag) throws IOException {

      int codeLength = 0;
      BerTag passedTag = berTag;

      if (berTag == null) {
        berTag = new BerTag();
        codeLength += berTag.decode(is);
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
        codeLength += BerLength.skip(is);
        component = new Component();
        codeLength += component.decode(is, null);
        return codeLength;
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
        index = new Unsigned32();
        codeLength += index.decode(is, false);
        return codeLength;
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 3)) {
        indexRange = new IndexRange();
        codeLength += indexRange.decode(is, false);
        return codeLength;
      }

      if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 4)) {
        allElements = new BerNull();
        codeLength += allElements.decode(is, false);
        return codeLength;
      }

      if (passedTag != null) {
        return 0;
      }

      throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
      ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
      encode(reverseOS);
      code = reverseOS.getArray();
    }

    public String toString() {
      StringBuilder sb = new StringBuilder();
      appendAsString(sb, 0);
      return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

      if (component != null) {
        sb.append("component: ");
        component.appendAsString(sb, indentLevel + 1);
        return;
      }

      if (index != null) {
        sb.append("index: ").append(index);
        return;
      }

      if (indexRange != null) {
        sb.append("indexRange: ");
        indexRange.appendAsString(sb, indentLevel + 1);
        return;
      }

      if (allElements != null) {
        sb.append("allElements: ").append(allElements);
        return;
      }

      sb.append("<none>");
    }

    public static class Component implements BerType, Serializable {

      private static final long serialVersionUID = 1L;

      public byte[] code = null;
      private BasicIdentifier basic = null;

      public Component() {}

      public Component(byte[] code) {
        this.code = code;
      }

      public BasicIdentifier getBasic() {
        return basic;
      }

      public void setBasic(BasicIdentifier basic) {
        this.basic = basic;
      }

      public int encode(OutputStream reverseOS) throws IOException {

        if (code != null) {
          for (int i = code.length - 1; i >= 0; i--) {
            reverseOS.write(code[i]);
          }
          return code.length;
        }

        int codeLength = 0;
        if (basic != null) {
          codeLength += basic.encode(reverseOS, true);
          return codeLength;
        }

        throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
      }

      public int decode(InputStream is) throws IOException {
        return decode(is, null);
      }

      public int decode(InputStream is, BerTag berTag) throws IOException {

        int codeLength = 0;
        BerTag passedTag = berTag;

        if (berTag == null) {
          berTag = new BerTag();
          codeLength += berTag.decode(is);
        }

        if (berTag.equals(BasicIdentifier.tag)) {
          basic = new BasicIdentifier();
          codeLength += basic.decode(is, false);
          return codeLength;
        }

        if (passedTag != null) {
          return 0;
        }

        throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
      }

      public void encodeAndSave(int encodingSizeGuess) throws IOException {
        ReverseByteArrayOutputStream reverseOS =
            new ReverseByteArrayOutputStream(encodingSizeGuess);
        encode(reverseOS);
        code = reverseOS.getArray();
      }

      public String toString() {
        StringBuilder sb = new StringBuilder();
        appendAsString(sb, 0);
        return sb.toString();
      }

      public void appendAsString(StringBuilder sb, int indentLevel) {

        if (basic != null) {
          sb.append("basic: ").append(basic);
          return;
        }

        sb.append("<none>");
      }
    }

    public static class IndexRange implements BerType, Serializable {

      public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
      private static final long serialVersionUID = 1L;
      public byte[] code = null;
      private Unsigned32 lowIndex = null;
      private Unsigned32 numberOfElements = null;

      public IndexRange() {}

      public IndexRange(byte[] code) {
        this.code = code;
      }

      public Unsigned32 getLowIndex() {
        return lowIndex;
      }

      public void setLowIndex(Unsigned32 lowIndex) {
        this.lowIndex = lowIndex;
      }

      public Unsigned32 getNumberOfElements() {
        return numberOfElements;
      }

      public void setNumberOfElements(Unsigned32 numberOfElements) {
        this.numberOfElements = numberOfElements;
      }

      public int encode(OutputStream reverseOS) throws IOException {
        return encode(reverseOS, true);
      }

      public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

        if (code != null) {
          for (int i = code.length - 1; i >= 0; i--) {
            reverseOS.write(code[i]);
          }
          if (withTag) {
            return tag.encode(reverseOS) + code.length;
          }
          return code.length;
        }

        int codeLength = 0;
        codeLength += numberOfElements.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 1
        reverseOS.write(0x81);
        codeLength += 1;

        codeLength += lowIndex.encode(reverseOS, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 0
        reverseOS.write(0x80);
        codeLength += 1;

        codeLength += BerLength.encodeLength(reverseOS, codeLength);

        if (withTag) {
          codeLength += tag.encode(reverseOS);
        }

        return codeLength;
      }

      public int decode(InputStream is) throws IOException {
        return decode(is, true);
      }

      public int decode(InputStream is, boolean withTag) throws IOException {
        int codeLength = 0;
        int subCodeLength = 0;
        BerTag berTag = new BerTag();

        if (withTag) {
          codeLength += tag.decodeAndCheck(is);
        }

        BerLength length = new BerLength();
        codeLength += length.decode(is);

        int totalLength = length.val;
        codeLength += totalLength;

        subCodeLength += berTag.decode(is);
        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
          lowIndex = new Unsigned32();
          subCodeLength += lowIndex.decode(is, false);
          subCodeLength += berTag.decode(is);
        } else {
          throw new IOException("Tag does not match the mandatory sequence element tag.");
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
          numberOfElements = new Unsigned32();
          subCodeLength += numberOfElements.decode(is, false);
          if (subCodeLength == totalLength) {
            return codeLength;
          }
        }
        throw new IOException(
            "Unexpected end of sequence, length tag: "
                + totalLength
                + ", actual sequence length: "
                + subCodeLength);
      }

      public void encodeAndSave(int encodingSizeGuess) throws IOException {
        ReverseByteArrayOutputStream reverseOS =
            new ReverseByteArrayOutputStream(encodingSizeGuess);
        encode(reverseOS, false);
        code = reverseOS.getArray();
      }

      public String toString() {
        StringBuilder sb = new StringBuilder();
        appendAsString(sb, 0);
        return sb.toString();
      }

      public void appendAsString(StringBuilder sb, int indentLevel) {

        sb.append("{");
        sb.append("\n");
        for (int i = 0; i < indentLevel + 1; i++) {
          sb.append("\t");
        }
        if (lowIndex != null) {
          sb.append("lowIndex: ").append(lowIndex);
        } else {
          sb.append("lowIndex: <empty-required-field>");
        }

        sb.append(",\n");
        for (int i = 0; i < indentLevel + 1; i++) {
          sb.append("\t");
        }
        if (numberOfElements != null) {
          sb.append("numberOfElements: ").append(numberOfElements);
        } else {
          sb.append("numberOfElements: <empty-required-field>");
        }

        sb.append("\n");
        for (int i = 0; i < indentLevel; i++) {
          sb.append("\t");
        }
        sb.append("}");
      }
    }
  }
}
