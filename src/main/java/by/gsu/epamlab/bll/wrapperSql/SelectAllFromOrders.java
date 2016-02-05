package by.gsu.epamlab.bll.wrapperSql;

import by.gsu.epamlab.constants.SQLQueries;


public class SelectAllFromOrders extends AbstractQuery {

    @Override
    public String getQuery() {
        return SQLQueries.SELECT_ALL_FROM_ORDERS;
    }
}
