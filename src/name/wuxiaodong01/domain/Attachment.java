package name.wuxiaodong01.domain;

import com.google.common.base.MoreObjects;

public class Attachment
{
    private String name;

    private byte[] contents;

    public void sayHello(){
        
    }

    public String getTrueName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public byte[] getContents()
    {
        return contents;
    }

    public void setContents(byte[] contents)
    {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(name).toString();
    }
}