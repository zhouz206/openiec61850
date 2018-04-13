/**
 * This class file was automatically generated by jASN1 v1.9.1-SNAPSHOT (http://www.openmuc.org)
 */

package org.openmuc.openiec61850.internal.mms.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.string.BerVisibleString;

public class ObjectName implements Serializable {

    private static final long serialVersionUID = 1L;

    public byte[] code = null;

    public static class DomainSpecific implements Serializable {

        private static final long serialVersionUID = 1L;

        public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

        public byte[] code = null;
        private Identifier domainID = null;
        private Identifier itemID = null;

        public DomainSpecific() {
        }

        public DomainSpecific(byte[] code) {
            this.code = code;
        }

        public void setDomainID(Identifier domainID) {
            this.domainID = domainID;
        }

        public Identifier getDomainID() {
            return domainID;
        }

        public void setItemID(Identifier itemID) {
            this.itemID = itemID;
        }

        public Identifier getItemID() {
            return itemID;
        }

        public int encode(OutputStream os) throws IOException {
            return encode(os, true);
        }

        public int encode(OutputStream os, boolean withTag) throws IOException {

            if (code != null) {
                for (int i = code.length - 1; i >= 0; i--) {
                    os.write(code[i]);
                }
                if (withTag) {
                    return tag.encode(os) + code.length;
                }
                return code.length;
            }

            int codeLength = 0;
            codeLength += itemID.encode(os, true);

            codeLength += domainID.encode(os, true);

            codeLength += BerLength.encodeLength(os, codeLength);

            if (withTag) {
                codeLength += tag.encode(os);
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
            if (berTag.equals(BerVisibleString.tag)) {
                domainID = new Identifier();
                subCodeLength += domainID.decode(is, false);
                subCodeLength += berTag.decode(is);
            }
            else {
                throw new IOException("Tag does not match the mandatory sequence element tag.");
            }

            if (berTag.equals(BerVisibleString.tag)) {
                itemID = new Identifier();
                subCodeLength += itemID.decode(is, false);
                if (subCodeLength == totalLength) {
                    return codeLength;
                }
            }
            throw new IOException("Unexpected end of sequence, length tag: " + totalLength
                    + ", actual sequence length: " + subCodeLength);

        }

        public void encodeAndSave(int encodingSizeGuess) throws IOException {
            ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
            encode(os, false);
            code = os.getArray();
        }

        @Override
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
            if (domainID != null) {
                sb.append("domainID: ").append(domainID);
            }
            else {
                sb.append("domainID: <empty-required-field>");
            }

            sb.append(",\n");
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            if (itemID != null) {
                sb.append("itemID: ").append(itemID);
            }
            else {
                sb.append("itemID: <empty-required-field>");
            }

            sb.append("\n");
            for (int i = 0; i < indentLevel; i++) {
                sb.append("\t");
            }
            sb.append("}");
        }

    }

    private Identifier vmdSpecific = null;
    private DomainSpecific domainSpecific = null;
    private Identifier aaSpecific = null;

    public ObjectName() {
    }

    public ObjectName(byte[] code) {
        this.code = code;
    }

    public void setVmdSpecific(Identifier vmdSpecific) {
        this.vmdSpecific = vmdSpecific;
    }

    public Identifier getVmdSpecific() {
        return vmdSpecific;
    }

    public void setDomainSpecific(DomainSpecific domainSpecific) {
        this.domainSpecific = domainSpecific;
    }

    public DomainSpecific getDomainSpecific() {
        return domainSpecific;
    }

    public void setAaSpecific(Identifier aaSpecific) {
        this.aaSpecific = aaSpecific;
    }

    public Identifier getAaSpecific() {
        return aaSpecific;
    }

    public int encode(OutputStream os) throws IOException {

        if (code != null) {
            for (int i = code.length - 1; i >= 0; i--) {
                os.write(code[i]);
            }
            return code.length;
        }

        int codeLength = 0;
        if (aaSpecific != null) {
            codeLength += aaSpecific.encode(os, false);
            // write tag: CONTEXT_CLASS, PRIMITIVE, 2
            os.write(0x82);
            codeLength += 1;
            return codeLength;
        }

        if (domainSpecific != null) {
            codeLength += domainSpecific.encode(os, false);
            // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
            os.write(0xA1);
            codeLength += 1;
            return codeLength;
        }

        if (vmdSpecific != null) {
            codeLength += vmdSpecific.encode(os, false);
            // write tag: CONTEXT_CLASS, PRIMITIVE, 0
            os.write(0x80);
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

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
            vmdSpecific = new Identifier();
            codeLength += vmdSpecific.decode(is, false);
            return codeLength;
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
            domainSpecific = new DomainSpecific();
            codeLength += domainSpecific.decode(is, false);
            return codeLength;
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
            aaSpecific = new Identifier();
            codeLength += aaSpecific.decode(is, false);
            return codeLength;
        }

        if (passedTag != null) {
            return 0;
        }

        throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
        ReverseByteArrayOutputStream os = new ReverseByteArrayOutputStream(encodingSizeGuess);
        encode(os);
        code = os.getArray();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendAsString(sb, 0);
        return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

        if (vmdSpecific != null) {
            sb.append("vmdSpecific: ").append(vmdSpecific);
            return;
        }

        if (domainSpecific != null) {
            sb.append("domainSpecific: ");
            domainSpecific.appendAsString(sb, indentLevel + 1);
            return;
        }

        if (aaSpecific != null) {
            sb.append("aaSpecific: ").append(aaSpecific);
            return;
        }

        sb.append("<none>");
    }

}
