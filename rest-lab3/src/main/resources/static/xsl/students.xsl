<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" />

    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Студенты</title>
            </head>
            <body>
                <h1>Список студентов</h1>

                <p>
                    <a href="/api/courses">Перейти к курсам (XML)</a>
                    |
                    <a href="/api/students">Обновить список студентов (XML)</a>
                </p>

                <table border="1" cellpadding="6">
                    <tr>
                        <th>ID</th>
                        <th>ФИО</th>
                        <th>Email</th>
                        <th>Возраст</th>
                        <th>Ссылка</th>
                    </tr>

                    <xsl:for-each select="students/student">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="fullName"/></td>
                            <td><xsl:value-of select="email"/></td>
                            <td><xsl:value-of select="age"/></td>
                            <td>
                                <a>
                                    <xsl:attribute name="href">/api/students/<xsl:value-of select="id"/></xsl:attribute>
                                    Открыть
                                </a>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
