import java.util.concurrent.locks.ReentrantLock;

public class SyncOverhead
{
	private static final int NUM_LOOPS = 1000000000;
	private static int cont = 0;
	private static Integer monitor = 1;
	private static ReentrantLock l = new ReentrantLock();

	public static void incrementCont()
	{
		for(int i = 0; i < NUM_LOOPS; i++)
			cont++;
	}

	public static void incrementSync()
	{
		for(int i = 0; i < NUM_LOOPS; i++)
		{
			synchronized(monitor)
			{
				cont++;
			}
		}
	}

	public static void incrementLock()
	{
		for(int i = 0; i < NUM_LOOPS; i++)
		{
			l.lock();
			try
			{
				cont++;
			}
			finally
			{
				l.unlock();
			}
		}
	}

	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		incrementCont();
		long elapsed = System.currentTimeMillis() - start;
		System.out.println("No sync: " + elapsed + " ms");

		cont = 0;

		start = System.currentTimeMillis();
		incrementSync();
		elapsed = System.currentTimeMillis() - start;
		System.out.println("Using synchronized block: " + elapsed + " ms");

		cont = 0;

		start = System.currentTimeMillis();
		incrementLock();
		elapsed = System.currentTimeMillis() - start;
		System.out.println("Using lock: " + elapsed + " ms");
	}
}
