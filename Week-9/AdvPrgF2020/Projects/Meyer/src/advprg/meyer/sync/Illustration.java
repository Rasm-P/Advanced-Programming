package advprg.meyer.sync;

public class Illustration
{
    public static void main(String[] args) throws InterruptedException
    {
        SyncBoxFactory syncBoxFactory = new SyncBoxFactory();
        SyncBox<String> stringBox = syncBoxFactory.getNewSyncBox();
        SyncBox<Long> longBox = syncBoxFactory.getNewSyncBox();
        SyncBox<Integer> fullStringBox = syncBoxFactory.getNewFullSyncBox(42);
    }
}
