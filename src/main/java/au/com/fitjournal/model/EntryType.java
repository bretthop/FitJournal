package au.com.fitjournal.model;

import au.com.fitjournal.util.StringUtil;

public enum EntryType
{
    MEAL,
    WORKOUT("-");

    private final String modifier;

    EntryType()
    {
        this("");
    }

    EntryType(String modifier)
    {
        this.modifier = modifier;
    }

    public String getModifier()
    {
        return modifier;
    }

    public String getFriendlyName()
    {
        return StringUtil.toStartCase(this.name());
    }
}
