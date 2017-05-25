package utils.threads

/**
 * @author ice1000
 * Created by ice1000 on 2016/5/25.
 */

class ProgressThread(private var setter: (Long) -> Unit) : Thread() {

	var running = true
	var nowTime = 0L

	/**
	 * this variable stores the time when the song is stopped
	 * test printing is deleted, because this prints too much information
	 */
	var storedTime: Long = 0L
		get() {
			return field + nowTime
		}

	override fun run() {
		running = true
		try {
			setter(storedTime)
		} catch(e: ArithmeticException) {
			return
		}
		val startTime = System.currentTimeMillis()
		while (running) {
			nowTime = System.currentTimeMillis() - startTime
			setter(storedTime)
			sleep(100)
		}
	}

}
