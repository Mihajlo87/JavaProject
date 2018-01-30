package ikt.learn.VideoKlub.Services;

import ikt.learn.VideoKlub.Exception.TotalCountException;
import ikt.learn.VideoKlub.Filter.OrderFilter;

public interface IOrderInterface {

	public Long updateOrder(OrderFilter filter) throws TotalCountException;
}
