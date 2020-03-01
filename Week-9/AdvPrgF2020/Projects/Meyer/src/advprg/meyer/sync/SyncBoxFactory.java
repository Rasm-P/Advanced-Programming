package advprg.meyer.sync;

public class SyncBoxFactory
{
    public <E> SyncBox<E> getNewSyncBox()
    {
        return new SyncBox<E>();
    }

    public <E> SyncBox<E> getNewFullSyncBox(E obj) throws InterruptedException
    {
        SyncBox<E> res = new SyncBox<E>();
        res.put(obj);
        return res;
    }
}
