package dev.surganov.bladesapi.util

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import dev.surganov.bladesapi.config.ConfigProvider
import org.jsoup.Jsoup
import org.jsoup.nodes.{Document, Element}
import org.jsoup.select.Elements

import java.nio.file.{Files, Paths}

object HtmlRendererHelper extends ConfigProvider {
  def renderReadmeToHtml: String = {
    // Read and convert README.md to HTML with custom classes
    val readmeContent: String = new String(Files.readAllBytes(Paths.get("README.md")))
    val parser: Parser = Parser.builder().build()
    val document = parser.parse(readmeContent)
    val renderer: HtmlRenderer = HtmlRenderer.builder().build()
    val rawHtml: String = renderer.render(document)

    // Parse the raw HTML using Jsoup to add custom classes
    val doc: Document = Jsoup.parse(rawHtml)
    addCustomClasses(doc)

    val readmeHtml: String = doc.body().html()

    // Combine HTML with custom CSS
    s"""
       |<!DOCTYPE html>
       |<html lang="en">
       |<head>
       |    <meta charset="UTF-8">
       |    <title>Blades in the Dark API</title>
       |    <style>
       |    ${readCustomCss()}
       |    </style>
       |</head>
       |<body>
       |<nav class="navbar">
       |  <a class="navbar-brand" href="#">Blades in the Dark API</a>
       |  <div class="navbar-nav">
       |    <a class="nav-link" href="#">Home</a> |
       |    <a class="nav-link" href="/swagger">API Docs</a>
       |  </div>
       |</nav>
       |<div class="container">
       |$readmeHtml
       |</div>
       |</body>
       |</html>
       |""".stripMargin
  }

  private def addCustomClasses(doc: Document): Unit = {
    // Add classes to headers
    val headers: Elements = doc.select("h1, h2, h3, h4, h5, h6")
    headers.forEach { header: Element =>
      header.addClass("mt-4 mb-2")
      header.tagName() match {
        case "h1" => header.addClass("display-4")
        case "h2" => header.addClass("display-5")
        case "h3" => header.addClass("display-6")
        case _    => header.addClass("h5")
      }
    }

    // Add classes to paragraphs
    val paragraphs: Elements = doc.select("p")
    paragraphs.forEach { paragraph: Element =>
      paragraph.addClass("mb-3")
    }

    // Add classes to code blocks
    val codeBlocks: Elements = doc.select("pre")
    codeBlocks.forEach { codeBlock: Element =>
      codeBlock.addClass("bg-dark text-white p-3 rounded")
    }

    // Add classes to inline code
    val inlineCode: Elements = doc.select("code")
    inlineCode.forEach { code: Element =>
      code.addClass("bg-light text-dark p-1 rounded")
    }

    // Add classes to links
    val links: Elements = doc.select("a")
    links.forEach { link: Element =>
      link.addClass("text-warning")
    }

    // Add classes to blockquotes
    val blockquotes: Elements = doc.select("blockquote")
    blockquotes.forEach { blockquote: Element =>
      blockquote.addClass("blockquote")
    }

    // Add classes to lists
    val lists: Elements = doc.select("ul, ol")
    lists.forEach { list: Element =>
      list.addClass("ms-4")
    }
  }

  private def readCustomCss(): String = {
    new String(Files.readAllBytes(Paths.get("src/main/resources/css/style.css")))
  }
}
