<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" />

    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Студент</title>
            </head>
            <body>
                <h1>Данные студента</h1>

                <p>
                    <a href="/api/students">← Назад к списку студентов (XML)</a>
                    |
                    <a href="/api/courses">Перейти к курсам (XML)</a>
                </p>

                <table border="1" cellpadding="6">
                    <tr><th>ID</th><td><xsl:value-of select="Student/id | student/id"/></td></tr>
                    <tr><th>ФИО</th><td><xsl:value-of select="Student/fullName | student/fullName"/></td></tr>
                    <tr><th>Email</th><td><xsl:value-of select="Student/email | student/email"/></td></tr>
                    <tr><th>Возраст</th><td><xsl:value-of select="Student/age | student/age"/></td></tr>
                </table>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
