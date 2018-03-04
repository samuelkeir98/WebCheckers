<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="10">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
  <div class="page">
  
    <h1>Web Checkers</h1>
    
    <div class="navigation">
        <#if currentPlayer??>
            <a href="/">my home</a> |
            <a href="/signout">sign out [${currentPlayer.name}]</a>
        <#else>
            <a href="/signin">sign in</a>
        </#if>
    </div>
    
    <div class="body">
      <p>Welcome to the world of online Checkers.</p>
      <p>Number of people playing: ${numPlayers}</p>
      <#if otherPlayers??>
        <ul>
        <#list otherPlayers as player>
          <li>${player.name}</li>
        </#list>
        </ul>
      </#if>
    </div>
    
  </div>
</body>
</html>
