package sgl
package awt

import java.net.URI
import java.awt.Desktop

trait AWTSystemProvider extends SystemProvider {

  override def exit(): Unit = {
    sys.exit()
  }

  override def loadTextResource(path: String): Iterator[String] = {
    val is = getClass.getClassLoader.getResourceAsStream(path)
    scala.io.Source.fromInputStream(is).getLines
  }

  override def openWebpage(uri: URI): Unit = {
    val desktop = if(Desktop.isDesktopSupported()) Desktop.getDesktop() else null
    if(desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
      try {
        desktop.browse(uri);
      } catch {
        case (e: Exception) =>
          e.printStackTrace()
      }
    }
  }
}
