package oop.focus.calendarhomepage.model;


import java.util.Objects;

/**
 * This class implements the HotKey interface that model a hot key. 
 * An HotKey object is represented by a String ,that is the hotKey name, and a category that is represented by a member of the HotKeyType enumeration.
 */
public class HotKeyImpl implements HotKey {

    private String name;
    private HotKeyType hotKeyType;

    /**
     * Class constructor.
     * @param s is the name of an hot key.
     * @param e is the type of an hot key.
     */
    public HotKeyImpl(final String s, final HotKeyType e) {
        this.name = s;
        this.hotKeyType = e;
    }

    public final String getName() {
        return this.name;
    }

    public final HotKeyType getType() {
        return this.hotKeyType;
    }

    public final void setName(final String newName) {
        this.name = newName;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final HotKeyImpl hotKey = (HotKeyImpl) o;
        return this.name.equals(hotKey.name) && this.hotKeyType == hotKey.hotKeyType;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.name, this.hotKeyType);
    }

    public final void setType(final String newValue) {
        this.hotKeyType = HotKeyType.getTypeFrom(newValue);
    }

}




