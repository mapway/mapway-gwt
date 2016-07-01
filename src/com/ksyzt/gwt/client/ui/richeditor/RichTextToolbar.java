/*
 * This software is published under the Apchae 2.0 licenses.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Author: Erik Scholtz
 * Web: http://blog.elitecoderz.net
 */

package com.ksyzt.gwt.client.ui.richeditor;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RichTextArea.Formatter;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

public class RichTextToolbar extends Composite {

	ImageUploadPanel m_dlg_image;

	HTMLPanel m_ap_shadow;
	HTMLPanel m_ap_content;
	HTMLPanel m_ap_line;
	boolean m_b_show_popup = false;

	Widget back_widget;
	String back_css;

	PopupPanel m_popup;

	private void showPopup(Widget attacheElement, Widget w) {
		back_widget = attacheElement;
		if (w == null) {
			w = new HTMLPanel("");
			w.setSize("100px", "100px");
			m_ap_content.clear();
			m_ap_content.add(w);
		} else {
			m_ap_content.clear();
			m_ap_content.add(w);
		}

		m_ap_content.setVisible(true);
		m_ap_shadow.setVisible(true);
		m_ap_line.setVisible(true);

		int aleft = attacheElement.getAbsoluteLeft();
		int atop = attacheElement.getAbsoluteTop();
		int aw = attacheElement.getOffsetWidth();
		int ah = attacheElement.getOffsetHeight();

		int width = m_ap_content.getOffsetWidth();
		int height = m_ap_content.getOffsetHeight();

		int lbx = aleft;
		int lby = atop + ah;
		int rbx = lbx + aw;
		int rby = lby;

		Style style = m_ap_content.getElement().getStyle();
		style.setLeft(rbx - width, Unit.PX);
		style.setTop(lby - 1, Unit.PX);

		style = m_ap_shadow.getElement().getStyle();
		style.setLeft(rbx - width + 3, Unit.PX);
		style.setTop(lby + 2, Unit.PX);
		style.setWidth(width, Unit.PX);
		style.setHeight(height, Unit.PX);

		style = m_ap_line.getElement().getStyle();
		style.setLeft(lbx + 1, Unit.PX);
		style.setTop(lby - 1, Unit.PX);
		style.setWidth(aw - 2, Unit.PX);

		back_css = attacheElement.getStyleName();
		attacheElement.setStyleName("toolbar_over");

		m_ap_content.getElement().focus();

		m_b_show_popup = true;

	}

	public void hidePopup() {
		m_ap_content.setVisible(false);
		m_ap_shadow.setVisible(false);
		m_ap_line.setVisible(false);

		if (back_widget != null) {
			m_b_show_popup = false;
			back_widget.setStyleName(back_css);
		}

	}

	private void initPopupPanel() {
		m_ap_shadow = new HTMLPanel("div", "");
		m_ap_content = new HTMLPanel("div", "");
		m_ap_line = new HTMLPanel("div", "");

		Style style = m_ap_content.getElement().getStyle();
		style.setProperty("border", "solid 1px skyblue");
		style.setBackgroundColor("white");
		style.setPadding(5, Unit.PX);
		style.setPosition(Position.ABSOLUTE);
		style.setZIndex(1000000);

		style = m_ap_shadow.getElement().getStyle();
		style.setBackgroundColor("#f0f0f0");
		style.setPosition(Position.ABSOLUTE);
		style.setZIndex(999999);

		m_ap_line.setHeight("1px");
		style = m_ap_line.getElement().getStyle();
		style.setBackgroundColor("white");
		style.setPosition(Position.ABSOLUTE);
		style.setZIndex(1000001);

		RootPanel.get().add(m_ap_shadow);
		RootPanel.get().add(m_ap_content);
		RootPanel.get().add(m_ap_line);

		m_ap_content.setVisible(false);

		m_ap_shadow.setVisible(false);

		m_ap_line.setVisible(false);

	}

	/** Local CONSTANTS **/
	// ImageMap and CSS related
	public static final String HTTP_STATIC_ICONS_GIF = GWT.getModuleBaseURL()
			+ "../images/toolbars.gif";
	private static final String CSS_ROOT_NAME = "RichTextToolbar";

	// Color and Fontlists - First Value (key) is the Name to display, Second
	// Value (value) is the HTML-Definition
	public final static HashMap<String, String> GUI_COLORLIST = new HashMap<String, String>();
	static {
		GUI_COLORLIST.put("White", "#FFFFFF");
		GUI_COLORLIST.put("Black", "#000000");
		GUI_COLORLIST.put("Red", "red");
		GUI_COLORLIST.put("Green", "green");
		GUI_COLORLIST.put("Yellow", "yellow");
		GUI_COLORLIST.put("Blue", "blue");
	}
	public final static HashMap<String, String> GUI_FONTLIST = new HashMap<String, String>();
	static {
		GUI_FONTLIST.put("Times New Roman", "Times New Roman");
		GUI_FONTLIST.put("Arial", "Arial");
		GUI_FONTLIST.put("Courier New", "Courier New");
		GUI_FONTLIST.put("Georgia", "Georgia");
		GUI_FONTLIST.put("Trebuchet", "Trebuchet");
		GUI_FONTLIST.put("Verdana", "Verdana");
	}

	// HTML Related (styles made by SPAN and DIV)
	private static final String HTML_STYLE_CLOSE_SPAN = "</span>";
	private static final String HTML_STYLE_CLOSE_DIV = "</div>";
	private static final String HTML_STYLE_OPEN_BOLD = "<span style=\"font-weight: bold;\">";
	private static final String HTML_STYLE_OPEN_ITALIC = "<span style=\"font-weight: italic;\">";
	private static final String HTML_STYLE_OPEN_UNDERLINE = "<span style=\"font-weight: underline;\">";
	private static final String HTML_STYLE_OPEN_LINETHROUGH = "<span style=\"font-weight: line-through;\">";
	private static final String HTML_STYLE_OPEN_ALIGNLEFT = "<div style=\"text-align: left;\">";
	private static final String HTML_STYLE_OPEN_ALIGNCENTER = "<div style=\"text-align: center;\">";
	private static final String HTML_STYLE_OPEN_ALIGNRIGHT = "<div style=\"text-align: right;\">";
	private static final String HTML_STYLE_OPEN_INDENTRIGHT = "<div style=\"margin-left: 40px;\">";

	// HTML Related (styles made by custom HTML-Tags)
	private static final String HTML_STYLE_OPEN_SUBSCRIPT = "<sub>";
	private static final String HTML_STYLE_CLOSE_SUBSCRIPT = "</sub>";
	private static final String HTML_STYLE_OPEN_SUPERSCRIPT = "<sup>";
	private static final String HTML_STYLE_CLOSE_SUPERSCRIPT = "</sup>";
	private static final String HTML_STYLE_OPEN_ORDERLIST = "<ol><li>";
	private static final String HTML_STYLE_CLOSE_ORDERLIST = "</ol></li>";
	private static final String HTML_STYLE_OPEN_UNORDERLIST = "<ul><li>";
	private static final String HTML_STYLE_CLOSE_UNORDERLIST = "</ul></li>";

	// HTML Related (styles without closing Tag)
	private static final String HTML_STYLE_HLINE = "<hr style=\"width: 100%; height: 2px;\">";

	// GUI Related stuff
	private static final String GUI_DIALOG_INSERTURL = "Enter a link URL:";
	private static final String GUI_DIALOG_IMAGEURL = "Enter an image URL:";

	private static final String GUI_LISTNAME_COLORS = "Colors";
	private static final String GUI_LISTNAME_FONTS = "Fonts";

	private static final String GUI_HOVERTEXT_SWITCHVIEW = "Switch View HTML/Source";
	private static final String GUI_HOVERTEXT_REMOVEFORMAT = "Remove Formatting";
	private static final String GUI_HOVERTEXT_IMAGE = "Insert Image";
	private static final String GUI_HOVERTEXT_HLINE = "Insert Horizontal Line";
	private static final String GUI_HOVERTEXT_BREAKLINK = "Break Link";
	private static final String GUI_HOVERTEXT_LINK = "Generate Link";
	private static final String GUI_HOVERTEXT_IDENTLEFT = "Ident Left";
	private static final String GUI_HOVERTEXT_IDENTRIGHT = "Ident Right";
	private static final String GUI_HOVERTEXT_UNORDERLIST = "Unordered List";
	private static final String GUI_HOVERTEXT_ORDERLIST = "Ordered List";
	private static final String GUI_HOVERTEXT_ALIGNRIGHT = "Align Right";
	private static final String GUI_HOVERTEXT_ALIGNCENTER = "Align Center";
	private static final String GUI_HOVERTEXT_ALIGNLEFT = "Align Left";
	private static final String GUI_HOVERTEXT_SUPERSCRIPT = "Superscript";
	private static final String GUI_HOVERTEXT_SUBSCRIPT = "Subscript";
	private static final String GUI_HOVERTEXT_STROKE = "Stroke";
	private static final String GUI_HOVERTEXT_UNDERLINE = "Underline";
	private static final String GUI_HOVERTEXT_ITALIC = "Italic";
	private static final String GUI_HOVERTEXT_BOLD = "Bold";

	/** Private Variables **/
	// The main (Vertical)-Panel and the two inner (Horizontal)-Panels
	private VerticalPanel outer;
	private HorizontalPanel topPanel;
	private HorizontalPanel bottomPanel;

	// The RichTextArea this Toolbar referes to and the Interfaces to access the
	// RichTextArea
	private RichTextArea styleText;
	private Formatter styleTextFormatter;

	// We use an internal class of the ClickHandler and the KeyUpHandler to be
	// private to others with these events
	private EventHandler evHandler;

	// The Buttons of the Menubar
	private ToggleButton bold;
	private ToggleButton italic;
	private ToggleButton underline;
	private ToggleButton stroke;
	private ToggleButton subscript;
	private ToggleButton superscript;
	private PushButton alignleft;
	private PushButton alignmiddle;
	private PushButton alignright;
	private PushButton orderlist;
	private PushButton unorderlist;
	private PushButton indentleft;
	private PushButton indentright;
	private PushButton generatelink;
	private PushButton breaklink;
	private PushButton insertline;
	private PushButton insertimage;
	private PushButton removeformatting;
	private ToggleButton texthtml;

	private ListBox fontlist;
	private ColorLabel colorlist;

	@UiConstructor
	public RichTextToolbar() {

		// HTTP_STATIC_ICONS_GIF=GWT.getModuleBaseURL()+
		// "../images/toolbars.gif";
		// Initialize the main-panel
		outer = new VerticalPanel();

		// Initialize the two inner panels
		topPanel = new HorizontalPanel();
		bottomPanel = new HorizontalPanel();
		topPanel.setStyleName(CSS_ROOT_NAME);
		bottomPanel.setStyleName(CSS_ROOT_NAME);

		// Save the reference to the RichText area we refer to and get the
		// interfaces to the stylings

		// Set some graphical options, so this toolbar looks how we like it.
		topPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		bottomPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);

		// Add the two inner panels to the main panel
		outer.add(topPanel);
		outer.add(bottomPanel);

		// Some graphical stuff to the main panel and the initialisation of the
		// new widget
		outer.setWidth("100%");
		outer.setStyleName(CSS_ROOT_NAME);
		initWidget(outer);

		//
		evHandler = new EventHandler();

		// Now lets fill the new toolbar with life
		buildTools();

		initPopupPanel();
	}

	public void setRichTextArea(RichTextArea area) {
		styleText = area;
		styleTextFormatter = styleText.getFormatter();
		styleText.addKeyUpHandler(evHandler);
		styleText.addClickHandler(evHandler);

	}

	private MessageHandler m_image_dlg_handler = new MessageHandler() {

		public void onMessage(Object sender, Integer message, Object value) {

			if (MessageEvent.CANCEL.equals(message)) {
				m_popup.hide(true);
			} else if (MessageEvent.OK.equals(message)) {
				String url = (String) value;
				if (!url.equals("")) {
					if (isHTMLMode()) {
						changeHtmlStyle(url, "");
					} else {
						styleTextFormatter.insertHTML(url);
					}
				}
				m_popup.hide(true);

			}
		}
	};

	/** Click Handler of the Toolbar **/
	private class EventHandler implements ClickHandler, KeyUpHandler,
			ChangeHandler {

		public void onClick(ClickEvent event) {
			if (event.getSource().equals(bold)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_BOLD, HTML_STYLE_CLOSE_SPAN);
				} else {
					styleTextFormatter.toggleBold();
				}
			} else if (event.getSource().equals(italic)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_ITALIC,
							HTML_STYLE_CLOSE_SPAN);
				} else {
					styleTextFormatter.toggleItalic();
				}
			} else if (event.getSource().equals(underline)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_UNDERLINE,
							HTML_STYLE_CLOSE_SPAN);
				} else {
					styleTextFormatter.toggleUnderline();
				}
			} else if (event.getSource().equals(stroke)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_LINETHROUGH,
							HTML_STYLE_CLOSE_SPAN);
				} else {
					styleTextFormatter.toggleStrikethrough();
				}
			} else if (event.getSource().equals(subscript)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_SUBSCRIPT,
							HTML_STYLE_CLOSE_SUBSCRIPT);
				} else {
					styleTextFormatter.toggleSubscript();
				}
			} else if (event.getSource().equals(superscript)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_SUPERSCRIPT,
							HTML_STYLE_CLOSE_SUPERSCRIPT);
				} else {
					styleTextFormatter.toggleSuperscript();
				}
			} else if (event.getSource().equals(alignleft)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_ALIGNLEFT,
							HTML_STYLE_CLOSE_DIV);
				} else {
					styleTextFormatter
							.setJustification(RichTextArea.Justification.LEFT);
				}
			} else if (event.getSource().equals(alignmiddle)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_ALIGNCENTER,
							HTML_STYLE_CLOSE_DIV);
				} else {
					styleTextFormatter
							.setJustification(RichTextArea.Justification.CENTER);
				}
			} else if (event.getSource().equals(alignright)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_ALIGNRIGHT,
							HTML_STYLE_CLOSE_DIV);
				} else {
					styleTextFormatter
							.setJustification(RichTextArea.Justification.RIGHT);
				}
			} else if (event.getSource().equals(orderlist)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_ORDERLIST,
							HTML_STYLE_CLOSE_ORDERLIST);
				} else {
					styleTextFormatter.insertOrderedList();
				}
			} else if (event.getSource().equals(unorderlist)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_UNORDERLIST,
							HTML_STYLE_CLOSE_UNORDERLIST);
				} else {
					styleTextFormatter.insertUnorderedList();
				}
			} else if (event.getSource().equals(indentright)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_OPEN_INDENTRIGHT,
							HTML_STYLE_CLOSE_DIV);
				} else {
					styleTextFormatter.rightIndent();
				}
			} else if (event.getSource().equals(indentleft)) {
				if (isHTMLMode()) {
					// TODO nothing can be done here at the moment
				} else {
					styleTextFormatter.leftIndent();
				}
			} else if (event.getSource().equals(generatelink)) {
				String url = Window.prompt(GUI_DIALOG_INSERTURL, "http://");
				if (url != null) {
					if (isHTMLMode()) {
						changeHtmlStyle("<a href=\"" + url + "\">", "</a>");
					} else {
						styleTextFormatter.createLink(url);
					}
				}
			} else if (event.getSource().equals(breaklink)) {
				if (isHTMLMode()) {
					// TODO nothing can be done here at the moment
				} else {
					styleTextFormatter.removeLink();
				}
			} else if (event.getSource().equals(insertimage)) {
				Widget w = (Widget) event.getSource();

				m_popup = new PopupPanel(true);

				if (m_b_show_popup == false) {
					if (m_dlg_image == null) {
						m_dlg_image = new ImageUploadPanel();
						m_dlg_image.addMessageHandler(m_image_dlg_handler);
					}
					m_popup.add(m_dlg_image);
					m_popup.showRelativeTo(w);
				} else {
					m_popup.hide(true);
				}

			} else if (event.getSource().equals(insertline)) {
				if (isHTMLMode()) {
					changeHtmlStyle(HTML_STYLE_HLINE, "");
				} else {
					styleTextFormatter.insertHorizontalRule();
				}
			} else if (event.getSource().equals(removeformatting)) {
				if (isHTMLMode()) {
					// TODO nothing can be done here at the moment
				} else {
					styleTextFormatter.removeFormat();
				}
			} else if (event.getSource().equals(texthtml)) {
				if (texthtml.isDown()) {
					styleText.setText(styleText.getHTML());
				} else {
					styleText.setHTML(styleText.getText());
				}
			} else if (event.getSource().equals(styleText)) {
				// Change invoked by the richtextArea
			}
			updateStatus();
		}

		public void onKeyUp(KeyUpEvent event) {
			updateStatus();
		}

		public void onChange(ChangeEvent event) {
			if (event.getSource().equals(fontlist)) {
				if (isHTMLMode()) {
					changeHtmlStyle(
							"<span style=\"font-family: "
									+ fontlist.getValue(fontlist
											.getSelectedIndex()) + ";\">",
							HTML_STYLE_CLOSE_SPAN);
				} else {
					styleTextFormatter.setFontName(fontlist.getValue(fontlist
							.getSelectedIndex()));
				}
			}
		}

	}

	/**
	 * Native JavaScript that returns the selected text and position of the
	 * start
	 **/
	public static native JsArrayString getSelection(Element elem) /*-{
		var txt = "";
		var pos = 0;
		var range;
		var parentElement;
		var container;

		if (elem.contentWindow.getSelection) {
			txt = elem.contentWindow.getSelection();
			pos = elem.contentWindow.getSelection().getRangeAt(0).startOffset;
		} else if (elem.contentWindow.document.getSelection) {
			txt = elem.contentWindow.document.getSelection();
			pos = elem.contentWindow.document.getSelection().getRangeAt(0).startOffset;
		} else if (elem.contentWindow.document.selection) {
			range = elem.contentWindow.document.selection.createRange();
			txt = range.text;
			parentElement = range.parentElement();
			container = range.duplicate();
			container.moveToElementText(parentElement);
			container.setEndPoint('EndToEnd', range);
			pos = container.text.length - range.text.length;
		}
		return [ "" + txt, "" + pos ];
	}-*/;

	/** Method called to toggle the style in HTML-Mode **/
	private void changeHtmlStyle(String startTag, String stopTag) {
		JsArrayString tx = getSelection(styleText.getElement());
		String txbuffer = styleText.getText();
		Integer startpos = Integer.parseInt(tx.get(1));
		String selectedText = tx.get(0);
		styleText.setText(txbuffer.substring(0, startpos) + startTag
				+ selectedText + stopTag
				+ txbuffer.substring(startpos + selectedText.length()));
	}

	/**
	 * Private method with a more understandable name to get if HTML mode is on
	 * or not
	 **/
	private Boolean isHTMLMode() {
		return texthtml.isDown();
	}

	/**
	 * Private method to set the toggle buttons and disable/enable buttons which
	 * do not work in html-mode
	 **/
	private void updateStatus() {
		if (styleTextFormatter != null) {
			bold.setDown(styleTextFormatter.isBold());
			italic.setDown(styleTextFormatter.isItalic());
			underline.setDown(styleTextFormatter.isUnderlined());
			subscript.setDown(styleTextFormatter.isSubscript());
			superscript.setDown(styleTextFormatter.isSuperscript());
			stroke.setDown(styleTextFormatter.isStrikethrough());
		}

		if (isHTMLMode()) {
			removeformatting.setEnabled(false);
			indentleft.setEnabled(false);
			breaklink.setEnabled(false);
		} else {
			removeformatting.setEnabled(true);
			indentleft.setEnabled(true);
			breaklink.setEnabled(true);
		}
	}

	/** Initialize the options on the toolbar **/
	private void buildTools() {
		// Init the TOP Panel forst
		topPanel.add(bold = createToggleButton(HTTP_STATIC_ICONS_GIF, 0, 0, 20,
				20, GUI_HOVERTEXT_BOLD));
		topPanel.add(italic = createToggleButton(HTTP_STATIC_ICONS_GIF, 0, 60,
				20, 20, GUI_HOVERTEXT_ITALIC));
		topPanel.add(underline = createToggleButton(HTTP_STATIC_ICONS_GIF, 0,
				140, 20, 20, GUI_HOVERTEXT_UNDERLINE));
		topPanel.add(stroke = createToggleButton(HTTP_STATIC_ICONS_GIF, 0, 120,
				20, 20, GUI_HOVERTEXT_STROKE));
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(subscript = createToggleButton(HTTP_STATIC_ICONS_GIF, 0,
				600, 20, 20, GUI_HOVERTEXT_SUBSCRIPT));
		topPanel.add(superscript = createToggleButton(HTTP_STATIC_ICONS_GIF, 0,
				620, 20, 20, GUI_HOVERTEXT_SUPERSCRIPT));
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(alignleft = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
				460, 20, 20, GUI_HOVERTEXT_ALIGNLEFT));
		topPanel.add(alignmiddle = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
				420, 20, 20, GUI_HOVERTEXT_ALIGNCENTER));
		topPanel.add(alignright = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
				480, 20, 20, GUI_HOVERTEXT_ALIGNRIGHT));
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(orderlist = createPushButton(HTTP_STATIC_ICONS_GIF, 0, 80,
				20, 20, GUI_HOVERTEXT_ORDERLIST));
		topPanel.add(unorderlist = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
				20, 20, 20, GUI_HOVERTEXT_UNORDERLIST));
		topPanel.add(indentright = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
				400, 20, 20, GUI_HOVERTEXT_IDENTRIGHT));
		topPanel.add(indentleft = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
				540, 20, 20, GUI_HOVERTEXT_IDENTLEFT));
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(generatelink = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
				500, 20, 20, GUI_HOVERTEXT_LINK));
		topPanel.add(breaklink = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
				640, 20, 20, GUI_HOVERTEXT_BREAKLINK));
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(insertline = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
				360, 20, 20, GUI_HOVERTEXT_HLINE));
		topPanel.add(insertimage = createPushButton(HTTP_STATIC_ICONS_GIF, 0,
				380, 20, 20, GUI_HOVERTEXT_IMAGE));
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(removeformatting = createPushButton(HTTP_STATIC_ICONS_GIF,
				0, 580, 20, 20, GUI_HOVERTEXT_REMOVEFORMAT));
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(texthtml = createToggleButton(HTTP_STATIC_ICONS_GIF, 0,
				260, 20, 20, GUI_HOVERTEXT_SWITCHVIEW));

		// Init the BOTTOM Panel
		topPanel.add(fontlist = createFontList());
		topPanel.add(new HTML("&nbsp;"));
		topPanel.add(colorlist = createColorList());
	}

	/** Method to create a Toggle button for the toolbar **/
	private ToggleButton createToggleButton(String url, Integer top,
			Integer left, Integer width, Integer height, String tip) {
		Image extract = new Image(url, left, top, width, height);
		ToggleButton tb = new ToggleButton(extract);
		tb.setStyleName("ksyzt-ToogleButton");
		tb.setHeight(height + "px");
		tb.setWidth(width + "px");
		tb.addClickHandler(evHandler);
		if (tip != null) {
			tb.setTitle(tip);
		}
		return tb;
	}

	/** Method to create a Push button for the toolbar **/
	private PushButton createPushButton(String url, Integer top, Integer left,
			Integer width, Integer height, String tip) {
		Image extract = new Image(url, left, top, width, height);

		PushButton tb = new PushButton(extract);
		tb.setStyleName("ksyzt-PushButton");
		tb.setHeight(height + "px");
		tb.setWidth(width + "px");
		tb.addClickHandler(evHandler);
		if (tip != null) {
			tb.setTitle(tip);
		}
		return tb;
	}

	/** Method to create the fontlist for the toolbar **/
	private ListBox createFontList() {
		ListBox mylistBox = new ListBox();
		mylistBox.addChangeHandler(evHandler);
		mylistBox.setVisibleItemCount(1);

		mylistBox.addItem(GUI_LISTNAME_FONTS);
		for (String name : GUI_FONTLIST.keySet()) {
			mylistBox.addItem(name, GUI_FONTLIST.get(name));
		}

		return mylistBox;
	}

	/** Method to create the colorlist for the toolbar **/
	private ColorLabel createColorList() {

		colorlist = new ColorLabel();
		Style style = colorlist.getElement().getStyle();

		style.setDisplay(Display.BLOCK);
		style.setPropertyPx("lineHeight", 14);
		style.setCursor(Cursor.POINTER);
		colorlist.setWidth("60px");

		colorlist.addMessageHandler(m_color_handler);
		return colorlist;
	}

	private MessageHandler m_color_handler = new MessageHandler() {

		@Override
		public void onMessage(Object sender, Integer message, Object value) {
			if (message == MessageEvent.COLOR) {
				String c = (String) value;

				if (isHTMLMode()) {
					changeHtmlStyle("<span style=\"color: " + c + ";\">",
							HTML_STYLE_CLOSE_SPAN);
				} else {
					styleTextFormatter.setForeColor(c);
				}
			}
		}
	};
}
