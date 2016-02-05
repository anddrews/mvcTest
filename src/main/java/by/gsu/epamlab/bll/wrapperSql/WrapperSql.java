package by.gsu.epamlab.bll.wrapperSql;

/**
 * Created by User on 04.02.2016.
 */
public abstract class WrapperSql extends AbstractQuery {

    protected AbstractQuery query;

    public WrapperSql(AbstractQuery query)
    {
        this.query=query;
    }

}
