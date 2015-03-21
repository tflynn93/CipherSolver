package flynn.tim.ciphersolver;

/**
 * Created by Tim on 3/20/2015.
 */
public class Result {

    String result;
    Boolean checked;
    Boolean ex;

    Result()
    {

    }

    Result(String r, Boolean b, Boolean c)
    {
        this.result = r;
        this.checked = b;
        this.ex = c;
    }

    public void setResult(String r)
    {
        this.result = r;
    }

    public String getResult()
    {
        return this.result;
    }

    public void setChecked(Boolean b)
    {
        this.checked = b;
    }

    public Boolean getChecked()
    {
        return this.checked;
    }

    public void setEx(Boolean b)
    {
        this.ex = b;
    }

    public boolean getEx()
    {
        return this.ex;
    }
}
