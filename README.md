# Minimal Android Browser

## Why?
In this age of increasing digitalisation, where our lives are being continually bound up with the 
internet, going back to basics, and learning to disconnect and “touch grass”, is more important than 
ever. This app aims at exactly that: removing the world from your fingertips, leaving only what 
little is necessary.

## What?
A simple Web-Page Viewer designed for opening a single Web-Page at a time. It will turn your 
smartphone from a pocket-computer back into a smart-phone.

## How?
Through an Android Application, using Jetpack Compose, a Web-View (Android WebView or Gecko WebView) 
and a local DataBase. Below is a list of features of the would-be application.

## MVP Features:

1. Open Web-Pages through:
    * Opening URLs from other apps
    * Scan and open QR codes
2. Disable searching / browsing / surfing the internet through a search or address bar
3. Disable Further Navigation.
    * Navigating from the initially opened webpage to any other URLs, even within the initial 
domain, is disallowed. Navigating within the initial web-page is allowed, through hyperlinks, to 
subsections of the page.
    * Ex.: Navigating within Wikipedia, from article to article is disabled, as is navigating from 
Wikipedia to Facebook. Navigating within a Single-Page Web-App would still be allowed.
4. Blacklist Websites. The user can set this list of disallowed websites.
   * Trying to open these will be disallowed.
   * They will be formatted using regex, meaning that any site matching the given pattern will not 
be allowed.
5. Comprehensively show errors
   * Through a snack-bar or separate screen
   * Handles: Internal errors (no internet connection, etc.), Invalid URLs, Blacklisted websites, 
Disallowed Navigation.
6. Password protect settings: All settings will be protected by a password / pin.
   * This would prevent the user from changing the settings by asking a parent or friend to set the 
password.

## Further Features

1. Customize Further Navigation. Add alternative options to the fully disabled setting:
   * Fully Enabled:
     * Navigating from the initially opened webpage to any URLs, through hyperlinks, searches or 
buttons, is allowed.
     * Ex: Navigating within Wikipedia, from Article to Article, or from Wikipedia to Facebook, is 
allowed.
   * Partially Enabled:
     * Navigating from the initially opened webpage to URLs within the initial domain, through 
hyperlinks, searches or buttons, is allowed.
     * Ex.: Navigating within Wikipedia, from article to article is allowed, but navigating from 
Wikipedia to Facebook is not allowed.
2. Visible URL bar
   * The text field is not editable, but copying the URL is possible.
3. Dark / Light mode
   * In the settings, address bar and errors.
4. Highlight color
   * In address bar, headers and notification bar. 
