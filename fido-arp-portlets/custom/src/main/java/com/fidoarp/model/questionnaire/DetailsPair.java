package com.fidoarp.model.questionnaire;

import java.text.Collator;
import java.util.Locale;

public class DetailsPair implements Comparable<DetailsPair> {

    private String left;

    private String right;

    private Locale locale;

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @return the left
     */
    public String getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(String left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public String getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(String right) {
        this.right = right;
    }

    public DetailsPair() {
    }

    public DetailsPair(String left, String right) {
        setLeft(left);
        setRight(right);
    }

    public DetailsPair(Locale locale, String left, String right) {
        setLeft(left);
        setRight(right);
        setLocale(locale);
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(DetailsPair pair) {
        if (pair == null) {
            return -1;
        } else {
            if (locale != null) {
                Collator collator = Collator.getInstance(locale);
                return collator.compare(getRight(), pair.getRight());
            } else {
                return getRight().compareToIgnoreCase(pair.getRight());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetailsPair that = (DetailsPair) o;

        if (left != null ? !left.equals(that.left) : that.left != null) return false;
        if (locale != null ? !locale.equals(that.locale) : that.locale != null) return false;
        if (right != null ? !right.equals(that.right) : that.right != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        return result;
    }
}
